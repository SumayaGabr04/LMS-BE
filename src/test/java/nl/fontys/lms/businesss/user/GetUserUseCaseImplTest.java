package nl.fontys.lms.businesss.user;

import nl.fontys.lms.business.exception.UserNotFoundException;
import nl.fontys.lms.business.user.GetUserUseCase;
import nl.fontys.lms.business.user.impl.GetUserUseCaseImpl;
import nl.fontys.lms.domain.user.GetUserRequest;
import nl.fontys.lms.domain.user.GetUserResponse;
import nl.fontys.lms.domain.user.User;
import nl.fontys.lms.persistence.UserRepository;
import nl.fontys.lms.persistence.entity.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class GetUserUseCaseImplTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private GetUserUseCaseImpl getUserUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getUserById_UserExists_ShouldReturnUserResponse() {
        // Arrange
        long userId = 1L;
        GetUserRequest request = GetUserRequest.builder().id(userId).build();
        UserEntity userEntity = createUserEntity(userId);
        when(userRepository.findById(userId)).thenReturn(Optional.of(userEntity));

        // Act
        GetUserResponse response = getUserUseCase.getUserById(request);

        // Assert
        assertNotNull(response);
        assertEquals(userId, response.getUser().getId());
        // Add more assertions based on your actual implementation and expected behavior
        verify(userRepository, times(1)).findById(userId);

    }

    @Test
    void getUserById_UserNotFound_ShouldThrowUserNotFoundException() {
        // Arrange
        long userId = 1L;
        GetUserRequest request = GetUserRequest.builder().id(userId).build();

        // Act & Assert
        assertThrows(UserNotFoundException.class, () -> getUserUseCase.getUserById(request));
    }


    private UserEntity createUserEntity(long userId) {
        return new UserEntity(userId, "ROLE_USER", "John", "Doe", "john.doe@example.com", "password_hash", null, null, null);
    }

}
