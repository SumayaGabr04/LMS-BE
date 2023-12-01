package nl.fontys.lms.businesss.course;

import nl.fontys.lms.business.course.impl.UpdateCourseUseCaseImpl;
import nl.fontys.lms.business.exception.InvalidCourseException;
import nl.fontys.lms.domain.course.UpdateCourseRequest;
import nl.fontys.lms.persistence.CourseRepository;
import nl.fontys.lms.persistence.entity.CourseEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class UpdateCourseUseCaseImplTest {

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private UpdateCourseUseCaseImpl updateCourseUseCase;

    @Test
    void testUpdateCourse() {
        // Arrange
        UpdateCourseRequest request = new UpdateCourseRequest();
        request.setId(1L);
        request.setCourseName("New Course");
        request.setDescription("New Description");
        request.setInstructor("New Instructor");
        request.setEnrollmentCapacity(50);
        request.setStartDate(new Date());
        request.setEndDate(new Date());

        CourseEntity existingCourse = CourseEntity.builder()
                .id(1L)
                .courseName("Old Course")
                .description("Old Description")
                .instructor("Old Instructor")
                .enrollmentCapacity(30)
                .startDate(new Date())
                .endDate(new Date())
                .build();

        when(courseRepository.findById(request.getId())).thenReturn(Optional.of(existingCourse));

        // Act
        updateCourseUseCase.UpdateCourse(request);

        // Assert
        verify(courseRepository, times(1)).findById(request.getId());
        verify(courseRepository, times(1)).save(any());

        assertEquals(request.getCourseName(), existingCourse.getCourseName());
        assertEquals(request.getDescription(), existingCourse.getDescription());
        assertEquals(request.getInstructor(), existingCourse.getInstructor());
        assertEquals(request.getEnrollmentCapacity(), existingCourse.getEnrollmentCapacity());
        assertEquals(request.getStartDate(), existingCourse.getStartDate());
        assertEquals(request.getEndDate(), existingCourse.getEndDate());
    }

    @Test
    void testUpdateCourseWithInvalidCourse() {
        // Arrange
        UpdateCourseRequest request = new UpdateCourseRequest();
        request.setId(1L);
        request.setCourseName("New Course");

        when(courseRepository.findById(request.getId())).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(InvalidCourseException.class, () -> updateCourseUseCase.UpdateCourse(request));

        // Verify that save is not called
        verify(courseRepository, never()).save(any());
    }
}
