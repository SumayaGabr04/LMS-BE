package nl.fontys.lms.businesss.enrollment;

import nl.fontys.lms.business.enrollment.impl.CreateEnrollmentUseCaseImpl;
import nl.fontys.lms.domain.enrollment.CreateEnrollmentRequest;
import nl.fontys.lms.domain.enrollment.CreateEnrollmentResponse;
import nl.fontys.lms.persistence.EnrollmentRepository;
import nl.fontys.lms.persistence.entity.EnrollmentEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateEnrollmentUseCaseImplTest {
    @Mock
    private EnrollmentRepository enrollmentRepository;

    @InjectMocks
    private CreateEnrollmentUseCaseImpl createEnrollmentUseCase;

//    @Test
//    void testCreateEnrollment_EnrollmentSuccess() {
//        // Arrange
//        CreateEnrollmentRequest request = new CreateEnrollmentRequest(1L, 2L, "Math");
//
//        // Mock behavior to indicate a successful enrollment
//        when(enrollmentRepository.isStudentEnrolledInCourse(2L, 1L)).thenReturn(false);
//        when(enrollmentRepository.save(Mockito.any(EnrollmentEntity.class))).thenReturn(createSampleEnrollmentEntity());
//
//        // Perform the test
//        CreateEnrollmentResponse response = createEnrollmentUseCase.createEnrollment(request);
//
//        // Assert that the response is not null
//        assertNotNull(response);
//
//        // Check if errorMessages is not null before calling isEmpty()
//        if (response.getErrorMessages() != null) {
//            assertTrue(response.getErrorMessages().isEmpty());
//        }
//
//        // Additional assertions based on your requirements
//        // For example, check that the enrollment ID is set in the response
//        assertNotNull(response.getEnrollmentId());
//    }

    // Helper method to create a sample EnrollmentEntity
    private EnrollmentEntity createSampleEnrollmentEntity() {
        // Implement logic to create a sample EnrollmentEntity
        return new EnrollmentEntity();
    }

    @Test
    void testCreateEnrollment_StudentAlreadyEnrolled() {
        // Arrange
        CreateEnrollmentRequest request = new CreateEnrollmentRequest(1L, 1L);

        // Set up mock behavior to simulate the student already being enrolled
        when(enrollmentRepository.isStudentEnrolledInCourse(request.getStudentId(), request.getCourseId())).thenReturn(true);

        // Act
        CreateEnrollmentResponse response = createEnrollmentUseCase.createEnrollment(request);

        // Assert
        assertTrue(response.getErrorMessages().contains("Student is already enrolled in the course"));

        // Verify that the isStudentEnrolledInCourse method was called with the correct arguments
        verify(enrollmentRepository).isStudentEnrolledInCourse(request.getStudentId(), request.getCourseId());

        // Verify that no other methods were called
        verifyNoMoreInteractions(enrollmentRepository);
    }

    @Test
    void testCreateEnrollment_FailedToCreateEnrollment() {
        // Arrange
        CreateEnrollmentRequest request = new CreateEnrollmentRequest(1L, 1L);

        // Set up mock behavior to simulate a failure to create the enrollment
        when(enrollmentRepository.isStudentEnrolledInCourse(request.getStudentId(), request.getCourseId())).thenReturn(false);
        when(enrollmentRepository.save(any())).thenReturn(null);

        // Act
        CreateEnrollmentResponse response = createEnrollmentUseCase.createEnrollment(request);

        // Assert
        assertTrue(response.getErrorMessages().contains("Failed to create the enrollment"));

        // Verify that the methods were called with the correct arguments
        verify(enrollmentRepository).isStudentEnrolledInCourse(request.getStudentId(), request.getCourseId());
        verify(enrollmentRepository).save(any());
    }
}
