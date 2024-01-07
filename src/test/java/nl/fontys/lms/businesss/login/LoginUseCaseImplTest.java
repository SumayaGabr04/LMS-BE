package nl.fontys.lms.businesss.login;

import nl.fontys.lms.business.exception.InvalidCredentialsException;
import nl.fontys.lms.business.login.impl.LoginUseCaseImpl;
import nl.fontys.lms.configuration.security.token.AccessTokenEncoder;
import nl.fontys.lms.configuration.security.token.impl.AccessTokenImpl;
import nl.fontys.lms.domain.login.LoginRequest;
import nl.fontys.lms.domain.login.LoginResponse;
import nl.fontys.lms.persistence.UserRepository;
import nl.fontys.lms.persistence.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoginUseCaseImplTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AccessTokenEncoder accessTokenEncoder;

    @InjectMocks
    private LoginUseCaseImpl loginUseCase;

    @Test
    void testLogin_ValidCredentials() {
        // Arrange
        String email = "test@example.com";
        String rawPassword = "password";
        String encodedPassword = "encodedPassword";
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(email);
        userEntity.setPasswordHash(encodedPassword);
        userEntity.setUserId(1L);
        userEntity.setRole("ROLE_USER");

        LoginRequest loginRequest = new LoginRequest(email, rawPassword);

        when(userRepository.findByEmail(email)).thenReturn(userEntity);
        when(passwordEncoder.matches(rawPassword, encodedPassword)).thenReturn(true);
        when(accessTokenEncoder.encode(any())).thenReturn("mockedAccessToken");

        // Act
        LoginResponse result = loginUseCase.login(loginRequest);

        // Assert
        assertEquals("mockedAccessToken", result.getAccessToken());

        // Verify that the methods were called with the correct arguments
        verify(userRepository).findByEmail(email);
        verify(passwordEncoder).matches(rawPassword, encodedPassword);
        verify(accessTokenEncoder).encode(new AccessTokenImpl(email, 1L, Collections.singletonList("ROLE_USER")));
    }

    @Test
    void testLogin_InvalidCredentials() {
        // Arrange
        String email = "nonexistent@example.com";
        String rawPassword = "wrongPassword";
        UserEntity userEntity = null; // Nonexistent user

        LoginRequest loginRequest = new LoginRequest(email, rawPassword);

        when(userRepository.findByEmail(email)).thenReturn(userEntity);

        // Act and Assert
        assertThrows(InvalidCredentialsException.class, () -> loginUseCase.login(loginRequest));

        // Verify that the findByEmail method was called with the correct argument
        verify(userRepository).findByEmail(email);

        // Verify that no other methods were called
        verifyNoInteractions(passwordEncoder, accessTokenEncoder);
    }

}
