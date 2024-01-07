package nl.fontys.lms.businesss.course;

import nl.fontys.lms.business.course.impl.TopCoursesUseCaseImpl;
import nl.fontys.lms.domain.course.TopCourseInfo;
import nl.fontys.lms.domain.course.TopCoursesResponse;
import nl.fontys.lms.persistence.CourseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TopCoursesUseCaseImplTest {
    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private TopCoursesUseCaseImpl topCoursesUseCase;

    @Test
    void getTop3CoursesWithMostEnrolledStudents() {
        // Mock data from the repository
        Object[] course1 = {"CourseA", 10L};
        Object[] course2 = {"CourseB", 8L};
        Object[] course3 = {"CourseC", 6L};
        List<Object[]> mockResult = Arrays.asList(course1, course2, course3);

        // Mock the behavior of the repository
        when(courseRepository.getTop3CoursesWithMostEnrolledStudents()).thenReturn(mockResult);

        // Call the use case method
        TopCoursesResponse result = topCoursesUseCase.getTop3CoursesWithMostEnrolledStudents();

        // Verify the result
        List<TopCourseInfo> expectedTopCourses = Arrays.asList(
                new TopCourseInfo("CourseA", 10L),
                new TopCourseInfo("CourseB", 8L),
                new TopCourseInfo("CourseC", 6L)
        );

        assertEquals(expectedTopCourses, result.getTopCourses());
    }

}
