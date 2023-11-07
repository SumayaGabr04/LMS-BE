package nl.fontys.lms.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.fontys.lms.persistence.EnrollmentRepository;
import nl.fontys.lms.persistence.entity.EnrollmentEntity;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



import java.util.Arrays;
import java.util.Date;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

@SpringBootTest
@AutoConfigureMockMvc
public class EnrollmentsControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void createEnrollment() throws Exception {
        EnrollmentEntity newEnrollment = EnrollmentEntity.builder()
                .id(1L) // Set a specific ID for testing
                .id(1L)
                .id(2L)
                .enrollmentDate(new Date())
                .build();

        // Mock the behavior of enrollmentRepository.save() to return an enrollment entity using Mockito
        Mockito.when(enrollmentRepository.save(Mockito.any(EnrollmentEntity.class)))
                .thenReturn(newEnrollment);

        String json = objectMapper.writeValueAsString(newEnrollment);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/enrollments")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON));

        resultActions.andExpect(status().isCreated());
    }

    @Test
    void getEnrollmentsForCourse() throws Exception {
        // Mock the behavior of enrollmentRepository.findAllEnrollmentsForCourse() to return a list of predefined EnrollmentEntity objects
        EnrollmentEntity enrollment1 = EnrollmentEntity.builder()
                .id(1L)
                .id(1L)
                .id(1L)
                .enrollmentDate(new Date())
                .build();
        EnrollmentEntity enrollment2 = EnrollmentEntity.builder()
                .id(2L)
                .id(2L)
                .id(1L)
                .enrollmentDate(new Date())
                .build();

        Mockito.when(enrollmentRepository.findAllEnrollmentsForCourse(1L)).thenReturn(Arrays.asList(enrollment1, enrollment2));

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/enrollments/course/1"));
        MvcResult mvcResult = resultActions.andExpect(status().isOk()).andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        // You can use a JSON parser library to verify the JSON content, or you can use JSONPath to simplify JSON checks.
        // For JSONPath, you can include your existing JSONPath expectations.
    }

    @Test
    void getEnrollmentsForStudent() throws Exception {
        // Mock the behavior of enrollmentRepository.findAllEnrollmentsForStudent() to return a list of predefined EnrollmentEntity objects
        EnrollmentEntity enrollment1 = EnrollmentEntity.builder()
                .id(1L)
                .id(1L)
                .id(1L)
                .enrollmentDate(new Date())
                .build();
        EnrollmentEntity enrollment2 = EnrollmentEntity.builder()
                .id(2L)
                .id(1L)
                .id(2L)
                .enrollmentDate(new Date())
                .build();

        Mockito.when(enrollmentRepository.findAllEnrollmentsForStudent(1L)).thenReturn(Arrays.asList(enrollment1, enrollment2));

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/enrollments/student/1"));
        MvcResult mvcResult = resultActions.andExpect(status().isOk()).andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        // You can use a JSON parser library to verify the JSON content, or you can use JSONPath to simplify JSON checks.
        // For JSONPath, you can include your existing JSONPath expectations.
    }

}
