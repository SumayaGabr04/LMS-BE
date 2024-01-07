package nl.fontys.lms.businesss.user;

import nl.fontys.lms.business.user.impl.GetAllUsersUseCaseImpl;
import nl.fontys.lms.domain.user.GetAllUsersResponse;
import nl.fontys.lms.persistence.UserRepository;
import nl.fontys.lms.persistence.entity.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class GetAllUsersUseCaseImplTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private GetAllUsersUseCaseImpl getAllUsersUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllUsers() {
        // Arrange
        ArrayList<UserEntity> userEntities = new ArrayList<>(Arrays.asList(
                new UserEntity(1L, "Student", "John", "Doe", "john.doe@example.com", "passwordHash", "Computer Science", null, null),
                new UserEntity(2L, "Teacher", "Jane", "Doe", "jane.doe@example.com", "passwordHash", null, "Mathematics", new Date())
        ));

        when(userRepository.findAll()).thenReturn(userEntities);

        // Act
        GetAllUsersResponse response = getAllUsersUseCase.getAllUsers();

        // Assert
        assertEquals(2, response.getUsers().size());
        assertEquals("John", response.getUsers().get(0).getFirstName());
        assertEquals("Doe", response.getUsers().get(0).getLastName());
        assertEquals("john.doe@example.com", response.getUsers().get(0).getEmail());
        assertEquals("Jane", response.getUsers().get(1).getFirstName());
        assertEquals("Doe", response.getUsers().get(1).getLastName());
        assertEquals("jane.doe@example.com", response.getUsers().get(1).getEmail());
    }
}
