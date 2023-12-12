package nl.fontys.lms.businesss.course;

import nl.fontys.lms.business.course.impl.CourseConverter;
import nl.fontys.lms.business.course.impl.GetAllCoursesUseCaseImpl;
import nl.fontys.lms.domain.course.Course;
import nl.fontys.lms.domain.course.GetAllCoursesResponse;
import nl.fontys.lms.persistence.CourseRepository;
import nl.fontys.lms.persistence.entity.CourseEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GetAllCoursesUseCaseImplTest {
    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private GetAllCoursesUseCaseImpl getAllCoursesUseCase;

    @Test
    void testGetCourses() {
        // Arrange
        CourseEntity courseEntity1 = CourseEntity.builder()
                .id(1L)
                .courseName("Course 1")
                .description("Description for Course 1")
                .instructor("Instructor 1")
                .enrollmentCapacity(30)
                .startDate(new Date())
                .endDate(new Date())
                .courseMaterials(new ArrayList<>())
                .enrolledStudents(new ArrayList<>())
                .build();

        CourseEntity courseEntity2 = CourseEntity.builder()
                .id(2L)
                .courseName("Course 2")
                .description("Description for Course 2")
                .instructor("Instructor 2")
                .enrollmentCapacity(25)
                .startDate(new Date())
                .endDate(new Date())
                .courseMaterials(new ArrayList<>())
                .enrolledStudents(new ArrayList<>())
                .build();

        ArrayList<CourseEntity> mockCourseEntities = new ArrayList<>(Arrays.asList(courseEntity1, courseEntity2));

        // Correct way to mock findAll
        when(courseRepository.findAll()).thenReturn(mockCourseEntities);

        // Act
        GetAllCoursesResponse response = getAllCoursesUseCase.getCourses();

        // Assert
        List<Course> expectedCourses = mockCourseEntities.stream()
                .map(CourseConverter::convert)
                .collect(Collectors.toList());

        assertEquals(expectedCourses, response.getCourses());
        verify(courseRepository, times(1)).findAll();
    }

    @Test
    void testSearchCourses() {
        // Arrange
        String searchTerm = "Course 1";
        CourseEntity courseEntity1 = CourseEntity.builder()
                .id(1L)
                .courseName("Course 1")
                .description("Description for Course 1")
                .instructor("Instructor 1")
                .enrollmentCapacity(30)
                .startDate(new Date())
                .endDate(new Date())
                .courseMaterials(new ArrayList<>())
                .enrolledStudents(new ArrayList<>())
                .build();

        ArrayList<CourseEntity> mockCourseEntities = new ArrayList<>(Arrays.asList(courseEntity1));

        // Mocking searchCourses
        when(courseRepository.searchCourses(searchTerm)).thenReturn(mockCourseEntities);

        // Act
        GetAllCoursesResponse response = getAllCoursesUseCase.searchCourses(searchTerm);

        // Assert
        List<Course> expectedCourses = mockCourseEntities.stream()
                .map(CourseConverter::convert)
                .collect(Collectors.toList());

        assertEquals(expectedCourses, response.getCourses());
        verify(courseRepository, times(1)).searchCourses(searchTerm);
    }
}
