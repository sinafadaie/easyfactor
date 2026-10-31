package com.easyfactor.identity.api;

import com.easyfactor.identity.api.dto.AuthResponse;
import com.easyfactor.identity.api.dto.TelegramAuthRequest;
import com.easyfactor.identity.api.dto.UserProfileResponse;
import com.easyfactor.identity.service.AuthService;
import com.easyfactor.security.AuthenticatedUser;
import com.easyfactor.shared.api.ApiResponse;

import jakarta.validation.Valid;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/telegram/owner")
    ApiResponse<AuthResponse> ownerLogin(@Valid @RequestBody TelegramAuthRequest request) {
        var session = authService.authenticateOwner(request.initData());
        return ApiResponse.ok(AuthResponse.from(session));
    }

    @GetMapping("/me")
    ApiResponse<UserProfileResponse> me(@AuthenticationPrincipal AuthenticatedUser user) {
        return ApiResponse.ok(UserProfileResponse.from(authService.getProfile(user)));
    }
}
