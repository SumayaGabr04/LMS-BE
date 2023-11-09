package nl.fontys.lms.business.course.impl;

import nl.fontys.lms.domain.course.Course;
import nl.fontys.lms.domain.course.GetAllEnrolledCoursesRequest;
import nl.fontys.lms.domain.course.GetAllEnrolledCoursesResponse;
import nl.fontys.lms.persistence.CourseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetEnrolledCoursesUseCaseImplTest {
    @InjectMocks
    private GetEnrolledCoursesUseCaseImpl getEnrolledCoursesUseCase;

    @Mock
    private CourseRepository fakeCourseRepository;

    @Test
    void testGetCourses() {
        // Arrange
        GetAllEnrolledCoursesRequest request = new GetAllEnrolledCoursesRequest(); // You can customize the request as needed

        List<Course> fakeCourses = createFakeCourses();
//        when(fakeCourseRepository.findAll()).thenReturn(fakeCourses);

        // Act
        GetAllEnrolledCoursesResponse response = getEnrolledCoursesUseCase.getCourses(request);

        // Assert
        List<Course> expectedCourses = createFakeCourses(); // create the expected courses
//        GetAllEnrolledCoursesResponse expectedResult = GetAllEnrolledCoursesResponse.builder().courses(expectedCourses).build();
//        assertEquals(expectedResult, response);
        verify(fakeCourseRepository).findAll(); // verify that the findAll method was called
    }

    private List<Course> createFakeCourses() {
        List<Course> courses = new ArrayList<>();
        courses.add(Course.builder().id(1L).courseName("Math101").instructor("John Doe").build());
        courses.add(Course.builder().id(2L).courseName("Physics202").instructor("Jane Smith").build());
        // Add more fake courses as needed
        return courses;
    }
}
