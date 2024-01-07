//package nl.fontys.lms.businesss.user;
//
//import nl.fontys.lms.business.exception.UserNotFoundException;
//import nl.fontys.lms.business.user.GetUserUseCase;
//import nl.fontys.lms.business.user.impl.GetUserUseCaseImpl;
//import nl.fontys.lms.domain.user.GetUserRequest;
//import nl.fontys.lms.domain.user.GetUserResponse;
//import nl.fontys.lms.domain.user.User;
//import nl.fontys.lms.persistence.UserRepository;
//import nl.fontys.lms.persistence.entity.UserEntity;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.ArgumentMatchers;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class GetUserUseCaseImplTest {
//    @Mock
//    private UserRepository userRepository;
//
//    @InjectMocks
//    private GetUserUseCaseImpl getUserUseCase;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        getUserUseCase = new GetUserUseCaseImpl(userRepository);
//    }
//
//    @Test
//    void testGetUserById() {
//        // Arrange
//        long userId = 1L;
//        GetUserRequest request = GetUserRequest.builder().id(userId).build();
//        UserEntity userEntity = buildUserEntity(userId);
//        when(userRepository.findById(eq(1L))).thenReturn(Optional.of(userEntity));
//
//        // Act
//        GetUserResponse response = getUserUseCase.getUserById(request);
//
//        // Assert
//        assertNotNull(response);
//        assertNotNull(response.getUser());
//        assertEquals(userId, response.getUser().getId());
//        assertEquals("John", response.getUser().getFirstName()); // Add more assertions as needed
//
//        // Verify that userRepository.findById was called with the correct ID
//        verify(userRepository, times(1)).findById(eq(1L));
//    }
//
//    @Test
//    void testGetUserById_UserNotFound() {
//        // Arrange
//        long userId = 1L;
//        GetUserRequest request = GetUserRequest.builder().id(userId).build();
//        when(userRepository.findById(eq(userId))).thenReturn(Optional.empty());
//
//        // Act & Assert
//        UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> getUserUseCase.getUserById(request));
//        assertEquals("invalid course name", exception.getReason());
//
//        // Verify that userRepository.findById was called with the correct ID
//        verify(userRepository, times(1)).findById(eq(userId));
//    }
//
//    private UserEntity buildUserEntity(long userId) {
//        return UserEntity.builder()
//                .userId(userId)
//                .role("student")
//                .firstName("John")
//                .lastName("Doe")
//                .email("john.doe@example.com")
//                .passwordHash("hashedPassword")
//                .build();
//    }
//}
