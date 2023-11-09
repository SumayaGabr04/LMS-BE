package nl.fontys.lms.business.course.impl;

import nl.fontys.lms.domain.course.Course;
import nl.fontys.lms.persistence.CourseRepository;
import nl.fontys.lms.persistence.entity.CourseEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetCourseUseCaseImplTest {
    @InjectMocks
    private GetCourseUseCaseImpl getCourseUseCase;

    @Mock
    private CourseRepository fakeCourseRepository;

    @Test
    void testGetCourse() {
        // Arrange
        long courseId = 1L;
        CourseEntity fakeCourseEntity = CourseEntity.builder()
                .id(courseId)
                .courseName("Math101")
                .instructor("John Doe")
                .build();

        when(fakeCourseRepository.findById(courseId)).thenReturn(Optional.of(fakeCourseEntity));

        // Act
        Optional<Course> result = getCourseUseCase.getCourse(courseId);

        // Assert
        verify(fakeCourseRepository).findById(courseId);
        assertEquals(fakeCourseEntity.getId(), result.orElseThrow().getId());
        assertEquals(fakeCourseEntity.getCourseName(), result.orElseThrow().getCourseName());
        assertEquals(fakeCourseEntity.getInstructor(), result.orElseThrow().getInstructor());
        // Add more assertions based on your specific requirements
    }
}
