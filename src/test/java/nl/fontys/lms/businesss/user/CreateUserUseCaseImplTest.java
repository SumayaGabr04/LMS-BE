package nl.fontys.lms.businesss.user;

import nl.fontys.lms.business.exception.EmailAlreadyExists;
import nl.fontys.lms.business.user.UserRole;
import nl.fontys.lms.business.user.impl.CreateUserUseCaseImpl;
import nl.fontys.lms.domain.user.CreateUserRequest;
import nl.fontys.lms.domain.user.CreateUserResponse;
import nl.fontys.lms.persistence.UserRepository;
import nl.fontys.lms.persistence.entity.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.never;

@ExtendWith(SpringExtension.class)
class CreateUserUseCaseImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    private CreateUserUseCaseImpl createUserUseCase;

    @BeforeEach
    void setUp() {
        createUserUseCase = new CreateUserUseCaseImpl(userRepository, passwordEncoder);
    }

    @Test
    void createUser_Success() {
        // Arrange
        CreateUserRequest request = CreateUserRequest.builder()
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@example.com")
                .password("password123")
                .role(UserRole.STUDENT)
                .build();

        when(userRepository.existsByEmail(request.getEmail())).thenReturn(false);
        when(passwordEncoder.encode(request.getPassword())).thenReturn("hashedPassword");
        when(userRepository.save(any(UserEntity.class))).thenReturn(UserEntity.builder().userId(1L).build());

        // Act
        CreateUserResponse response = createUserUseCase.createUser(request);

        // Assert
        assertNotNull(response);
        assertEquals(1L, response.getId());

        verify(userRepository, times(1)).existsByEmail(request.getEmail());
        verify(passwordEncoder, times(1)).encode(request.getPassword());
        verify(userRepository, times(1)).save(any(UserEntity.class));
    }

    @Test
    void createUser_EmailAlreadyExists() {
        // Arrange
        CreateUserRequest request = CreateUserRequest.builder()
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@example.com")
                .password("password123")
                .role(UserRole.STUDENT)
                .build();

        when(userRepository.existsByEmail(request.getEmail())).thenReturn(true);

        // Act and Assert
        assertThrows(EmailAlreadyExists.class, () -> createUserUseCase.createUser(request));

        verify(userRepository, times(1)).existsByEmail(request.getEmail());
        verify(passwordEncoder, never()).encode(anyString());
        verify(userRepository, never()).save(any(UserEntity.class));
    }
}
