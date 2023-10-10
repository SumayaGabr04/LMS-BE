package nl.fontys.lms.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.fontys.lms.persistence.CourseRepository;
import nl.fontys.lms.persistence.entity.CourseEntity;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import java.util.*;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class CoursesControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CourseRepository courseRepository;

    @Autowired
    private ObjectMapper objectMapper; // ObjectMapper to convert objects to JSON

    @Test
    public void createCourse() throws Exception {
        CourseEntity newCourse = CourseEntity.builder()
                .id(1L) // Set a specific ID for testing
                .courseName("New Course")
                .description("Course description")
                .instructor("Instructor Name")
                .enrollmentCapacity(30)
                .startDate(new Date())
                .endDate(new Date())
                .courseMaterials(new ArrayList<>())
                .build();

        // Mock the behavior of courseRepository.save() to return a course entity using  mockito
        Mockito.when(courseRepository.save(Mockito.any(CourseEntity.class)))
                .thenReturn(newCourse);

        String json = objectMapper.writeValueAsString(newCourse);

        mockMvc.perform(MockMvcRequestBuilders.post("/courses")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void getCourse() throws Exception {
        // Mock the behavior of courseRepository.findById() to return a predefined CourseEntity
        CourseEntity course = CourseEntity.builder()
                .id(1L)
                .courseName("Course 1")
                .build();
        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));

        mockMvc.perform(MockMvcRequestBuilders.get("/courses/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.courseName").value("Course 1"));
    }


    @Test
    void getAllCourses() throws Exception {
        // Mock the behavior of courseRepository.findAll() to return a list of predefined CourseEntity objects
        ArrayList<CourseEntity> courses = new ArrayList<>(Arrays.asList(
                CourseEntity.builder().id(1L).courseName("Course 1").build(),
                CourseEntity.builder().id(2L).courseName("Course 2").build()
        ));
        when(courseRepository.findAll()).thenReturn(courses);

        mockMvc.perform(MockMvcRequestBuilders.get("/courses"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.courses", hasSize(2))) // Use "$.courses" to access the array
                .andExpect(jsonPath("$.courses[0].id").value(1))
                .andExpect(jsonPath("$.courses[0].courseName").value("Course 1"))
                .andExpect(jsonPath("$.courses[1].id").value(2))
                .andExpect(jsonPath("$.courses[1].courseName").value("Course 2"));
    }


    @Test
    void deleteCourse() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/courses/1"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    void updateCourse() {
    }
}