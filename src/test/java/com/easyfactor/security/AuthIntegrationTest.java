package com.easyfactor.security;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import com.easyfactor.identity.domain.UserRole;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

/**
 * End-to-end check of the auth plumbing: a real JWT through the filter chain
 * must reach an owner endpoint with our AuthenticatedUser as principal.
 */
@SpringBootTest
@Testcontainers
class AuthIntegrationTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:17");

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    @Autowired
    private JwtService jwtService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .addFilters(springSecurityFilterChain)
                .build();
    }

    @Test
    void authenticatedOwnerTokenReachesOwnerEndpoints() throws Exception {
        AuthenticatedUser user = new AuthenticatedUser(UUID.randomUUID(), 12345L, UserRole.OWNER, "Ali");
        String token = jwtService.issue(user).value();

        mockMvc.perform(get("/api/v1/owner/products")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }

    @Test
    void issuedTokenParsesBackToPrincipal() {
        AuthenticatedUser user = new AuthenticatedUser(UUID.randomUUID(), 12345L, UserRole.OWNER, "Ali");
        String token = jwtService.issue(user).value();

        AuthenticatedUser parsed = jwtService.parse(token);

        org.assertj.core.api.Assertions.assertThat(parsed.accountId()).isEqualTo(user.accountId());
        org.assertj.core.api.Assertions.assertThat(parsed.role()).isEqualTo(UserRole.OWNER);
    }

    @Test
    void requestWithoutTokenIsRejected() throws Exception {
        mockMvc.perform(get("/api/v1/owner/products"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void requestWithGarbageTokenIsRejected() throws Exception {
        mockMvc.perform(get("/api/v1/owner/products")
                        .header("Authorization", "Bearer garbage"))
                .andExpect(status().isUnauthorized());
    }
}
