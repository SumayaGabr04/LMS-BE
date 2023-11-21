package nl.fontys.lms.businesss.user;

import nl.fontys.lms.business.exception.UserNotFoundException;
import nl.fontys.lms.business.user.impl.GetUserUseCaseImpl;
import nl.fontys.lms.domain.user.GetUserRequest;
import nl.fontys.lms.domain.user.GetUserResponse;
import nl.fontys.lms.domain.user.User;
import nl.fontys.lms.persistence.UserRepository;
import nl.fontys.lms.persistence.entity.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GetUserUseCaseImplTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private GetUserUseCaseImpl getUserUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getUserById_UserFound() {
        // Arrange
        GetUserRequest request = new GetUserRequest(1L);
        UserEntity userEntity = new UserEntity(1L, "Student", "John", "Doe", "john.doe@example.com", "passwordHash", "Computer Science", null, null);

        when(userRepository.findById(request.getId())).thenReturn(Optional.of(userEntity));

        // Act
        GetUserResponse response = getUserUseCase.getUserById(request);

        // Assert
        assertTrue(response.getUser().isPresent());
        User user = response.getUser().get();
        assertEquals("John", user.getFirstName());
        assertEquals("Doe", user.getLastName());
        assertEquals("john.doe@example.com", user.getEmail());

        // Verify that userRepository.findById was called with the correct ID
        verify(userRepository, times(1)).findById(request.getId());
    }

    @Test
    void getUserById_UserNotFound() {
        // Arrange
        GetUserRequest request = new GetUserRequest(1L);

        when(userRepository.findById(request.getId())).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(UserNotFoundException.class, () -> getUserUseCase.getUserById(request));

        // Verify that userRepository.findById was called with the correct ID
        verify(userRepository, times(1)).findById(request.getId());
    }
}
