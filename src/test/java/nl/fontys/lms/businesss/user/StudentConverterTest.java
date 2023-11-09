package nl.fontys.lms.businesss.user;

import nl.fontys.lms.business.user.student.impl.StudentConverter;
import nl.fontys.lms.domain.user.student.Student;
import nl.fontys.lms.persistence.entity.StudentEntity;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StudentConverterTest {
    @Test
    void convert_shouldConvertStudentEntityToStudent() {
        // Arrange
        StudentEntity mockEntity = mock(StudentEntity.class);
        when(mockEntity.getUserId()).thenReturn(1L);
        when(mockEntity.getFirstName()).thenReturn("John");
        when(mockEntity.getLastName()).thenReturn("Doe");
        when(mockEntity.getEmail()).thenReturn("john.doe@example.com");
        when(mockEntity.getPasswordHash()).thenReturn("hashedPassword");
        when(mockEntity.getPasswordSalt()).thenReturn("passwordSalt");
        when(mockEntity.getCoursesEnrolled()).thenReturn(null); // You can mock courses if needed
        when(mockEntity.getMajor()).thenReturn("Computer Science");

        // Act
        Student result = StudentConverter.convert(mockEntity);

        // Assert
        assertEquals(1L, result.getId());
        assertEquals("John", result.getFirstName());
        assertEquals("Doe", result.getLastName());
        assertEquals("john.doe@example.com", result.getEmail());
        assertEquals("hashedPassword", result.getPasswordHash());
        assertEquals("passwordSalt", result.getPasswordSalt());
        assertEquals(new ArrayList<>(), result.getCoursesEnrolled());
        assertEquals("Computer Science", result.getMajor());
    }
}
