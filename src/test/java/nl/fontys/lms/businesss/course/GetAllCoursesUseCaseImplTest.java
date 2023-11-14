//package nl.fontys.lms.businesss.course;
//
//import nl.fontys.lms.business.course.impl.GetAllCoursesUseCaseImpl;
//import nl.fontys.lms.domain.course.Course;
//import nl.fontys.lms.domain.course.GetAllCoursesResponse;
//import nl.fontys.lms.persistence.CourseRepository;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//public class GetAllCoursesUseCaseImplTest {
//    @InjectMocks
//    private GetAllCoursesUseCaseImpl getAllCoursesUseCase;
//
//    @Mock
//    private CourseRepository fakeCourseRepository;
//
//    @Test
//    void testGetCourses() {
//        // Arrange
//        List<Course> fakeCourses = createFakeCourses();
////        when(fakeCourseRepository.findAll()).thenReturn(fakeCourses);
//
//        // Act
//        GetAllCoursesResponse response = getAllCoursesUseCase.getCourses();
//
//        // Assert
//        List<Course> expectedCourses = createFakeCourses(); // create the expected courses
//        GetAllCoursesResponse expectedResult = GetAllCoursesResponse.builder().courses(new ArrayList<>(expectedCourses)).build();
//        assertEquals(expectedResult, response);
//        verify(fakeCourseRepository).findAll(); // verify that the findAll method was called
//    }
//
//    private List<Course> createFakeCourses() {
//        List<Course> courses = new ArrayList<>();
//        courses.add(Course.builder()
//                .id(1L)
//                .courseName("Math101")
//                .description("description1")
//                .instructor("John Doe")
//                .enrollmentCapacity(30)
//                .startDate(new Date())
//                .endDate(new Date())
//                .courseMaterials(new ArrayList<>())
//                .build());
//
//        courses.add(Course.builder()
//                .id(2L)
//                .courseName("Physics202")
//                .description("description2")
//                .instructor("Jane Smith")
//                .enrollmentCapacity(25)
//                .startDate(new Date())
//                .endDate(new Date())
//                .courseMaterials(new ArrayList<>())
//                .build());
//
//        return courses;
//    }
//
//}
