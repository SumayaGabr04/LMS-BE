package nl.fontys.lms.business.course.impl;

import nl.fontys.lms.business.course.CreateCourseUseCase;
import nl.fontys.lms.business.course.validations.CreateCourseRequestValidator;
import nl.fontys.lms.business.course.validations.ValidationResult;
import nl.fontys.lms.domain.course.CreateCourseRequest;
import nl.fontys.lms.domain.course.CreateCourseResponse;
import nl.fontys.lms.persistence.CourseRepository;
import nl.fontys.lms.persistence.entity.CourseEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class CreateCourseUseCaseImplTest {
    @MockBean
    private CourseRepository fakeCourseRepository;

    @MockBean
    private CreateCourseRequestValidator fakeRequestValidator;

    @Autowired
    private CreateCourseUseCaseImpl createCourseUseCase;

    @BeforeEach
    void setUp() {
        // Initialize the mocks
        MockitoAnnotations.openMocks(this);

        // Inject the mock into the class under test
        createCourseUseCase = new CreateCourseUseCaseImpl(fakeCourseRepository, fakeRequestValidator);
    }

    @Test
    public void testCreateCourse_Success() {
        // Arrange
        CreateCourseRequest request = new CreateCourseRequest(
                "Math101",
                "Math Course",
                "John Doe",
                30,
                java.util.Date.from(LocalDate.of(2023, 9, 1).atStartOfDay(ZoneId.systemDefault()).toInstant()), // Convert LocalDate to Date
                java.util.Date.from(LocalDate.of(2023, 12, 31).atStartOfDay(ZoneId.systemDefault()).toInstant())
        );

        // Configure the mock to return a valid ValidationResult with an empty list of error messages
        when(fakeRequestValidator.validateCourseRequest(Mockito.any())).thenReturn(new ValidationResult(new ArrayList<>()));


        // Act
        CreateCourseResponse response = createCourseUseCase.createCourse(request);

        // Assert
        // Verify that existsByCourseName was called with the expected argument
        verify(fakeCourseRepository).existsByCourseName("Math101");

        // Verify other interactions
        verify(fakeCourseRepository).save(Mockito.any(CourseEntity.class));
        assertEquals(1L, response.getCourseId());
    }

}