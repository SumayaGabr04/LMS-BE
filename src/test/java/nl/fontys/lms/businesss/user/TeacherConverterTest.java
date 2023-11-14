package nl.fontys.lms.businesss.user;

import nl.fontys.lms.business.user.teacher.impl.TeacherConverter;
import nl.fontys.lms.domain.user.teacher.Teacher;
import nl.fontys.lms.persistence.entity.TeacherEntity;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TeacherConverterTest {
//    @Test
//    void convert_shouldConvertTeacherEntityToTeacher() throws ParseException {
//        // Arrange
//        TeacherEntity mockEntity = mock(TeacherEntity.class);
//        when(mockEntity.getUserId()).thenReturn(1L);
//        when(mockEntity.getFirstName()).thenReturn("Jane");
//        when(mockEntity.getLastName()).thenReturn("Smith");
//        when(mockEntity.getEmail()).thenReturn("jane.smith@example.com");
//        when(mockEntity.getPasswordHash()).thenReturn("hashedPassword");
//        when(mockEntity.getPasswordSalt()).thenReturn("passwordSalt");
//        when(mockEntity.getDepartment()).thenReturn("Computer Science");
//
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Date hireDate = dateFormat.parse("2022-01-01");
//        when(mockEntity.getHireDate()).thenReturn(hireDate);
//
//        // Act
//        Teacher result = TeacherConverter.convert(mockEntity);
//
//        // Assert
//        assertEquals(1L, result.getId());
//        assertEquals("Jane", result.getFirstName());
//        assertEquals("Smith", result.getLastName());
//        assertEquals("jane.smith@example.com", result.getEmail());
//        assertEquals("hashedPassword", result.getPasswordHash());
//        assertEquals("passwordSalt", result.getPasswordSalt());
//        assertEquals(new ArrayList<>(), result.getCoursesTaught()); // Adjust if needed
//        assertEquals("Computer Science", result.getDepartment());
//        assertEquals("2022-01-01", result.getHireDate()); // Adjust date format as needed
//    }
}
