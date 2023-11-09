package nl.fontys.lms.business.course.impl;

import nl.fontys.lms.business.course.UpdateCourseUseCase;
import nl.fontys.lms.business.exception.InvalidCourseException;
import nl.fontys.lms.domain.course.UpdateCourseRequest;
import nl.fontys.lms.persistence.CourseRepository;
import nl.fontys.lms.persistence.entity.CourseEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UpdateCourseUseCaseImplTest {
    @InjectMocks
    private UpdateCourseUseCase updateCourseUseCase;

    @Mock
    private CourseRepository fakeCourseRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUpdateCourse_Success() {
        // Arrange
        UpdateCourseRequest request = new UpdateCourseRequest(
                1L,
                "Math101",
                "Updated Math Course",
                "John Doe",
                40,
                java.util.Date.from(LocalDate.of(2023, 9, 1).atStartOfDay(ZoneId.systemDefault()).toInstant()),
                java.util.Date.from(LocalDate.of(2023, 12, 31).atStartOfDay(ZoneId.systemDefault()).toInstant())
        );

        CourseEntity existingCourse = CourseEntity.builder()
                .id(1L)
                .courseName("Math101")
                .description("Math Course")
                .instructor("John Doe")
                .enrollmentCapacity(30)
                .startDate(java.util.Date.from(LocalDate.of(2023, 1, 1).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .endDate(java.util.Date.from(LocalDate.of(2023, 12, 31).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();

        when(fakeCourseRepository.findById(request.getId())).thenReturn(Optional.of(existingCourse));

        // Act
        updateCourseUseCase.UpdateCourse(request);

        // Assert
        verify(fakeCourseRepository).save(existingCourse);
        assertEquals(request.getCourseName(), existingCourse.getCourseName());
        assertEquals(request.getDescription(), existingCourse.getDescription());
        assertEquals(request.getInstructor(), existingCourse.getInstructor());
        assertEquals(request.getEnrollmentCapacity(), existingCourse.getEnrollmentCapacity());
        assertEquals(request.getStartDate(), existingCourse.getStartDate());
        assertEquals(request.getEndDate(), existingCourse.getEndDate());
    }

    @Test
    void testUpdateCourse_InvalidCourse() {
        // Arrange
        UpdateCourseRequest request = new UpdateCourseRequest(
                1L,
                "Math101",
                "Updated Math Course",
                "John Doe",
                40,
                java.util.Date.from(LocalDate.of(2023, 9, 1).atStartOfDay(ZoneId.systemDefault()).toInstant()),
                java.util.Date.from(LocalDate.of(2023, 12, 31).atStartOfDay(ZoneId.systemDefault()).toInstant())
        );

        when(fakeCourseRepository.findById(request.getId())).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(InvalidCourseException.class, () -> updateCourseUseCase.UpdateCourse(request));
    }
}
