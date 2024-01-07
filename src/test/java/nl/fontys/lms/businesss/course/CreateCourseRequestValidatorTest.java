package nl.fontys.lms.businesss.course;

import nl.fontys.lms.business.course.validations.CreateCourseRequestValidator;
import nl.fontys.lms.business.course.validations.ValidationResult;
import nl.fontys.lms.domain.course.CreateCourseRequest;
import nl.fontys.lms.persistence.CourseRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class CreateCourseRequestValidatorTest {
    @Mock
    private CourseRepository fakeCourseRepository;

    @InjectMocks
    private CreateCourseRequestValidator validator;

    @Test
    void testValidateCourseRequest_CourseNameExists() {
        // Arrange
        CreateCourseRequest request = new CreateCourseRequest(
                "Math101",
                "Math Course",
                "John Doe",
                30,
                java.util.Date.from(LocalDate.of(2024, 9, 1).atStartOfDay(ZoneId.systemDefault()).toInstant()),
                java.util.Date.from(LocalDate.of(2024, 12, 1).atStartOfDay(ZoneId.systemDefault()).toInstant())
        );

        // Configure the mock to return true when existsByCourseName is called
        when(fakeCourseRepository.existsByCourseName("Math101")).thenReturn(true);

        // Act
        ValidationResult validationResult = validator.validateCourseRequest(request);

        // Assert
        assertEquals(Collections.singletonList("Course already exists."), validationResult.getErrorMessages());
    }
}
