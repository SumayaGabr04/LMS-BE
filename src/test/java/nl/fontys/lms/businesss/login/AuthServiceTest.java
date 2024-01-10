package nl.fontys.lms.businesss.login;

import nl.fontys.lms.business.login.impl.AuthService;
import nl.fontys.lms.configuration.security.token.AccessToken;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AuthServiceTest {
    @Test
    void testGetAuthenticatedUserInRequest() {
        // Mock AccessToken
        AccessToken accessToken = mock(AccessToken.class);
        when(accessToken.getUserId()).thenReturn(1L);

        // Mock Authentication
        Authentication authentication = new UsernamePasswordAuthenticationToken("user", "password", null);
        authentication = new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials(), authentication.getAuthorities());
        ((UsernamePasswordAuthenticationToken) authentication).setDetails(accessToken);

        // Mock SecurityContext
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);

        // Set the mocked SecurityContext to SecurityContextHolder
        SecurityContextHolder.setContext(securityContext);

        // Create an instance of the AuthService
        AuthService authService = new AuthService();

        // Test the getAuthenticatedUserInRequest method
        AccessToken result = authService.getAuthenticatedUserInRequest();

        // Assertions
        assertEquals(accessToken, result);
        assertEquals(1L, result.getUserId());

        // Reset the SecurityContextHolder to avoid interference with other tests
        SecurityContextHolder.clearContext();
    }

    @Test
    void testGetAuthenticatedUserInRequestWhenNotAuthenticated() {
        // Mock SecurityContext without authentication
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(null);

        // Set the mocked SecurityContext to SecurityContextHolder
        SecurityContextHolder.setContext(securityContext);

        // Create an instance of the AuthService
        AuthService authService = new AuthService();

        // Test the getAuthenticatedUserInRequest method when not authenticated
        AccessToken result = authService.getAuthenticatedUserInRequest();

        // Assertions
        assertNull(result);

        // Reset the SecurityContextHolder to avoid interference with other tests
        SecurityContextHolder.clearContext();
    }
}
