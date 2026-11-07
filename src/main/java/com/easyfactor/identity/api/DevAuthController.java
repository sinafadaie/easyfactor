package com.easyfactor.identity.api;

import com.easyfactor.identity.api.dto.AuthResponse;
import com.easyfactor.identity.api.dto.DevTokenRequest;
import com.easyfactor.identity.domain.Owner;
import com.easyfactor.identity.domain.OwnerRepository;
import com.easyfactor.identity.domain.UserProfile;
import com.easyfactor.identity.domain.UserRole;
import com.easyfactor.identity.service.AuthSession;
import com.easyfactor.security.AuthenticatedUser;
import com.easyfactor.security.IssuedToken;
import com.easyfactor.security.JwtService;
import com.easyfactor.shared.error.BusinessException;
import com.easyfactor.shared.error.ErrorCode;
import com.easyfactor.shared.error.NotFoundException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
we used ResponseEntity as our return type of our endpoint here,
but we used ApiResponse on another controller (it is our custom class)
because this endpoint is just for devlop, that's ok,
but we should stick to our standard
 */
@RestController
@RequestMapping("/api/v1/auth/dev")
@ConditionalOnProperty(name = "app.dev-token.enabled", havingValue = "true")    //this is dev endpoint
public class DevAuthController {

    private final OwnerRepository ownerRepository;
    private final JwtService jwtService;

    public DevAuthController(OwnerRepository ownerRepository, JwtService jwtService) {
        this.ownerRepository = ownerRepository;
        this.jwtService = jwtService;
    }

    @PostMapping("/token")      //this is post, because Get has not Body!
    public ResponseEntity<AuthResponse> devToken(@RequestBody DevTokenRequest request) {
        System.out.println("hi");
        Owner owner = resolveOwner(request);
        AuthenticatedUser authenticatedUser = new AuthenticatedUser(owner.getId(), owner.getTelegramUserId(), UserRole.OWNER, displayNameOf(owner));
        IssuedToken token = jwtService.issue(authenticatedUser);
        AuthSession authSession = new AuthSession(token.value(), "Bearer", token.expiresAt(), authenticatedUser, toProfile(owner));
        return new ResponseEntity<>(AuthResponse.from(authSession), HttpStatus.OK);
}

    private Owner resolveOwner(DevTokenRequest request) {
        if (request.accountId() != null)
            return ownerRepository.findById(request.accountId())
                    .orElseThrow(()-> new NotFoundException("owner",request.accountId()));
        if (request.telegramUserId() != null)
            return ownerRepository.findByTelegramUserId(request.telegramUserId())
                    .orElseThrow(()-> new NotFoundException("owner" , request.telegramUserId()));
        throw new BusinessException(ErrorCode.VALIDATION_FAILED , "telegramUserId or accountId is required");
    }

    private String displayNameOf(Owner owner){
        if (owner.getFirstName() != null && !owner.getFirstName().isBlank())
            return owner.getFirstName();

        if (owner.getTelegramUsername() != null)
            return owner.getTelegramUsername();
        else
            return "Owner";
    }

    private UserProfile toProfile(Owner owner){
        return new UserProfile(
                owner.getId(),
                owner.getTelegramUserId(),
                UserRole.OWNER,
                owner.getFirstName(),
                owner.getTelegramUsername(),
                owner.getShopName());
    }

}
