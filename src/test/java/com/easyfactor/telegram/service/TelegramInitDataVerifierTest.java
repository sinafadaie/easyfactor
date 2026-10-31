package com.easyfactor.telegram.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.HexFormat;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import com.easyfactor.config.TelegramProperties;
import com.easyfactor.telegram.domain.InvalidInitDataException;
import com.easyfactor.telegram.domain.TelegramUser;

import org.junit.jupiter.api.Test;

import tools.jackson.databind.json.JsonMapper;

class TelegramInitDataVerifierTest {

    private static final String BOT_TOKEN = "123456:test-bot-token";

    private final TelegramInitDataVerifier verifier = new TelegramInitDataVerifier(
            new TelegramProperties(BOT_TOKEN, "testbot", null, null, Duration.ofHours(24)),
            new JsonMapper(),
            Clock.systemUTC());

    @Test
    void verifiesValidInitData() {
        String userJson = "{\"id\":12345,\"first_name\":\"Ali\",\"last_name\":\"Rezaei\","
                + "\"username\":\"ali_shop\",\"language_code\":\"fa\",\"is_premium\":false}";
        String initData = sign(Map.of("auth_date", String.valueOf(Instant.now().getEpochSecond()),
                "query_id", "AAHdF6IQAAAAAN0XohDhrOrc", "user", userJson));

        TelegramUser user = verifier.verify(initData);

        assertThat(user.id()).isEqualTo(12345);
        assertThat(user.firstName()).isEqualTo("Ali");
        assertThat(user.lastName()).isEqualTo("Rezaei");
        assertThat(user.username()).isEqualTo("ali_shop");
        assertThat(user.languageCode()).isEqualTo("fa");
        assertThat(user.premium()).isFalse();
    }

    @Test
    void rejectsTamperedData() {
        String userJson = "{\"id\":12345,\"first_name\":\"Ali\"}";
        String initData = sign(Map.of("auth_date", String.valueOf(Instant.now().getEpochSecond()),
                "user", userJson));
        String tampered = initData.replace("first_name", "first_namX");

        assertThatThrownBy(() -> verifier.verify(tampered))
                .isInstanceOf(InvalidInitDataException.class);
    }

    @Test
    void rejectsExpiredInitData() {
        String userJson = "{\"id\":12345,\"first_name\":\"Ali\"}";
        long oldAuthDate = Instant.now().minus(Duration.ofHours(25)).getEpochSecond();
        String initData = sign(Map.of("auth_date", String.valueOf(oldAuthDate), "user", userJson));

        assertThatThrownBy(() -> verifier.verify(initData))
                .isInstanceOfSatisfying(InvalidInitDataException.class, e -> {
                    assertThat(e.args()[0].toString()).contains("expired");
                });
    }

    @Test
    void rejectsMissingHash() {
        assertThatThrownBy(() -> verifier.verify("auth_date=1&user=%7B%7D"))
                .isInstanceOfSatisfying(InvalidInitDataException.class, e -> {
                    assertThat(e.args()[0].toString()).contains("hash");
                });
    }

    private String sign(Map<String, String> params) {
        String dataCheckString = new TreeMap<>(params).entrySet().stream()
                .map(e -> e.getKey() + "=" + e.getValue())
                .collect(Collectors.joining("\n"));
        String secret = hmac("WebAppData", BOT_TOKEN);
        String hash = hmac(secret, dataCheckString);
        return params.entrySet().stream()
                .map(e -> e.getKey() + "=" + URLEncoder.encode(e.getValue(), StandardCharsets.UTF_8))
                .collect(Collectors.joining("&"))
                + "&hash=" + hash;
    }

    private static String hmac(String key, String data) {
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
            return HexFormat.of().formatHex(mac.doFinal(data.getBytes(StandardCharsets.UTF_8)));
        } catch (GeneralSecurityException e) {
            throw new IllegalStateException(e);
        }
    }
}
