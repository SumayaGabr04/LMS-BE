package nl.fontys.lms.business.login.impl;

import nl.fontys.lms.configuration.security.token.AccessToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    public AccessToken getAuthenticatedUserInRequest() {
        // Get the current security context
        SecurityContext context = SecurityContextHolder.getContext();

        // Check if there's an authentication object in the context
        if (context != null) {
            Authentication authentication = context.getAuthentication();

            // Check if the authentication object contains the user details
            if (authentication != null && authentication.getDetails() instanceof AccessToken) {
                // Return the AccessToken containing user information
                return (AccessToken) authentication.getDetails();
            }
        }
        return null;
    }
}
