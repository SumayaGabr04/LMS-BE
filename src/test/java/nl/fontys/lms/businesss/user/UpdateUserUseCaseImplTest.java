package nl.fontys.lms.businesss.user;

import nl.fontys.lms.business.exception.UserNotFoundException;
import nl.fontys.lms.business.user.impl.UpdateUserUseCaseImpl;
import nl.fontys.lms.domain.user.UpdateUserRequest;
import nl.fontys.lms.persistence.UserRepository;
import nl.fontys.lms.persistence.entity.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class UpdateUserUseCaseImplTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UpdateUserUseCaseImpl updateUserUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void updateUser_UserFound() {
        // Arrange
        UpdateUserRequest request = UpdateUserRequest.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@example.com")
                .password("newPassword")
                .build();


        UserEntity existingUser = new UserEntity(1L, "Student", "OldFirstName", "OldLastName", "old.email@example.com", "oldPassword", null, null, null);

        when(userRepository.findById(request.getId())).thenReturn(Optional.of(existingUser));
        when(passwordEncoder.encode(request.getPassword())).thenReturn("hashedNewPassword");
        when(userRepository.save(existingUser)).thenReturn(existingUser);

        // Act
        updateUserUseCase.updateUser(request);

        // Assert
        assertEquals("John", existingUser.getFirstName());
        assertEquals("Doe", existingUser.getLastName());
        assertEquals("john.doe@example.com", existingUser.getEmail());
        assertEquals("hashedNewPassword", existingUser.getPasswordHash());

        // Verify that userRepository.findById was called with the correct ID
        verify(userRepository, times(1)).findById(request.getId());
        // Verify that passwordEncoder.encode was called with the correct password
        verify(passwordEncoder, times(1)).encode(request.getPassword());
        // Verify that userRepository.save was called with the updated user
        verify(userRepository, times(1)).save(existingUser);
    }

    @Test
    void updateUser_UserNotFound() {
        // Arrange
        UpdateUserRequest request = UpdateUserRequest.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@example.com")
                .password("newPassword")
                .build();


        when(userRepository.findById(request.getId())).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(UserNotFoundException.class, () -> updateUserUseCase.updateUser(request));

        // Verify that userRepository.findById was called with the correct ID
        verify(userRepository, times(1)).findById(request.getId());
        // Verify that passwordEncoder.encode and userRepository.save were not called
        verify(passwordEncoder, never()).encode(anyString());
        verify(userRepository, never()).save(any(UserEntity.class));
    }
}
