package nl.fontys.lms.businesss.course;

import nl.fontys.lms.business.course.impl.DeleteCourseUseCaseImpl;
import nl.fontys.lms.persistence.CourseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class DeleteCourseUseCaseImplTest {

    @InjectMocks
    private DeleteCourseUseCaseImpl deleteCourseUseCase;

    @Mock
    private CourseRepository fakeCourseRepository;

    @Test
    void testDeleteCourse() {
        // Arrange
        long courseId = 1L;

        // Act
        deleteCourseUseCase.deleteCourse(courseId);

        // Assert
        verify(fakeCourseRepository).deleteById(courseId);
    }
}
