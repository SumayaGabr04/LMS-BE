package nl.fontys.lms.controller;

import nl.fontys.lms.business.course.*;
import nl.fontys.lms.domain.course.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CoursesControllerTest {

    @Mock
    private GetCourseUseCase getCourseUseCase;

    @Mock
    private GetAllCoursesUseCase getAllCoursesUseCase;

    @Mock
    private DeleteCourseUseCase deleteCourseUseCase;

    @Mock
    private CreateCourseUseCase createCourseUseCase;

    @Mock
    private UpdateCourseUseCase updateCourseUseCase;

    @InjectMocks
    private CoursesController coursesController;

    @Test
    @WithMockUser(username = "student@example.com", password = "password123", roles = {"STUDENT"})
    void getCourse_shouldReturn200WithCourse_whenCourseFound() {
        long courseId = 1L;
        Course course = new Course();
        when(getCourseUseCase.getCourse(courseId)).thenReturn(Optional.of(course));

        ResponseEntity<Course> response = coursesController.getCourse(courseId);

        verify(getCourseUseCase).getCourse(courseId);
        assertAll(() -> {
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals(course, response.getBody());
        });
    }

    @Test
    @WithMockUser(username = "admin@example.com", password = "password123", roles = {"ADMIN"})
    void getAllCourses_shouldReturn200WithCoursesList() {
        List<Course> courses = List.of(new Course(), new Course());
        GetAllCoursesResponse responseDTO = GetAllCoursesResponse.builder().courses(new ArrayList<>(courses)).build();

        when(getAllCoursesUseCase.getCourses()).thenReturn(responseDTO);

        ResponseEntity<GetAllCoursesResponse> response = coursesController.getAllCourses();

        verify(getAllCoursesUseCase).getCourses();
        assertAll(() -> {
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals(responseDTO, response.getBody());
        });
    }

    @Test
    @WithMockUser(username = "admin@example.com", password = "password123", roles = {"ADMIN"})
    void deleteCourse_shouldReturn204() {
        int courseId = 1;
        ResponseEntity<Void> response = coursesController.deleteCourse(courseId);

        verify(deleteCourseUseCase).deleteCourse(courseId);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    @WithMockUser(username = "admin@example.com", password = "password123", roles = {"ADMIN"})
    void createCourse_shouldReturn201_whenRequestIsValid() {
        CreateCourseRequest request = new CreateCourseRequest();
        CreateCourseResponse response = CreateCourseResponse.builder().courseId(1L).build();

        when(createCourseUseCase.createCourse(request)).thenReturn(response);

        ResponseEntity<CreateCourseResponse> createResponse = coursesController.createCourse(request);

        verify(createCourseUseCase).createCourse(request);
        assertAll(() -> {
            assertEquals(HttpStatus.CREATED, createResponse.getStatusCode());
            assertEquals(response, createResponse.getBody());
        });
    }

    @Test
    @WithMockUser(username = "admin@example.com", password = "password123", roles = {"ADMIN"})
    void updateCourse_shouldReturn204() {
        long courseId = 1L;
        UpdateCourseRequest request = new UpdateCourseRequest();

        ResponseEntity<Void> response = coursesController.updateCourse(courseId, request);

        verify(updateCourseUseCase).UpdateCourse(request);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }


}
