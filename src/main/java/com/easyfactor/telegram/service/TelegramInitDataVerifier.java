package com.easyfactor.telegram.service;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.HexFormat;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import com.easyfactor.config.TelegramProperties;
import com.easyfactor.telegram.domain.InvalidInitDataException;
import com.easyfactor.telegram.domain.TelegramUser;

import tools.jackson.core.JacksonException;
import tools.jackson.databind.json.JsonMapper;

import org.springframework.stereotype.Component;

/**
 * Parses and HMAC-verifies Mini App initData and returns the Telegram user.
 * Secret = HMAC_SHA256("WebAppData", botToken); hash = HMAC_SHA256(secret, dataCheckString).
 */
@Component
public class TelegramInitDataVerifier {

    private final TelegramProperties properties;
    private final JsonMapper jsonMapper;
    private final Clock clock;

    public TelegramInitDataVerifier(TelegramProperties properties, JsonMapper jsonMapper, Clock clock) {
        this.properties = properties;
        this.jsonMapper = jsonMapper;
        this.clock = clock;
    }

    public TelegramUser verify(String rawInitData) {                //telegram give this raw data
        Map<String, String> params = parseQuery(rawInitData);

        String providedHash = params.get("hash");                   //we should have "hash" as a key on raw data
        if (providedHash == null || providedHash.isBlank()) {
            throw new InvalidInitDataException("hash is missing");
        }


        String dataCheckString = params.entrySet().stream()                               //Telegram’s official algorithm for validating initData requires below step.
                .filter(entry -> !entry.getKey().equals("hash"))        //1.To verify Telegram’s signature, you must take all parameters except hash
                .sorted(Map.Entry.comparingByKey())                                       //2.sort them by key.
                .map(entry -> entry.getKey() + "=" + entry.getValue())  //3.format them as key=value.
                .collect(Collectors.joining("\n"));                               //4.join them together with \n.

        String secretKey = hmacSha256("WebAppData", properties.token());             //"WebAppData" act like seed
        String expectedHash = hmacSha256(secretKey, dataCheckString);                     //properties.token() provided by BotFather (when we created the bot)
        if (!MessageDigest.isEqual(
                expectedHash.getBytes(StandardCharsets.UTF_8),                            //MessageDigest.isEqual() is safer than equal()
                providedHash.getBytes(StandardCharsets.UTF_8))) {                         //because of timing attack.
            throw new InvalidInitDataException("hash mismatch");                          //equal() checks often stop as soon as they find the first difference.
        }                                                                                 //so time can vary depending on how many beginning characters match, it is dangerous
                                                                                          //MessageDigest.isEqual() is safer for cryptographic comparisons

        String authDateRaw = params.get("auth_date");
        if (authDateRaw == null) {
            throw new InvalidInitDataException("auth_date is missing");
        }
        Instant authDate = Instant.ofEpochSecond(Long.parseLong(authDateRaw));            //start time
        Duration maxAge = properties.initDataMaxAge();                                    //allowed duration time
        if (authDate.plus(maxAge).isBefore(clock.instant())) {
            throw new InvalidInitDataException("initData is expired");
        }

        String userJson = params.get("user");
        if (userJson == null) {
            throw new InvalidInitDataException("user is missing");
        }
        try {
            return jsonMapper.readValue(userJson, TelegramUser.class);   //convert JSON string → Java object
        } catch (JacksonException e) {
            throw new InvalidInitDataException("invalid user JSON");
        }
    }

    private static Map<String, String> parseQuery(String raw) {     //parse raw data to some key/value
        Map<String, String> params = new LinkedHashMap<>();
        for (String pair : raw.split("&")) {
            int eq = pair.indexOf('=');
            if (eq < 0) {
                continue;
            }
            String key = pair.substring(0, eq);
            String value;
            try {
                value = URLDecoder.decode(pair.substring(eq + 1), StandardCharsets.UTF_8);
            } catch (IllegalArgumentException e) {
                throw new InvalidInitDataException("malformed initData");
            }
            params.put(key, value);
        }
        return params;
    }

    private static String hmacSha256(String key, String data) { // HMAC = Hash-based Message Authentication Code
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
            return HexFormat.of().formatHex(mac.doFinal(data.getBytes(StandardCharsets.UTF_8)));
        } catch (GeneralSecurityException e) {
            throw new IllegalStateException("HMAC-SHA256 is unavailable", e);
        }
    }
}
