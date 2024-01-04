package nl.fontys.lms.businesss.enrollment;

import nl.fontys.lms.business.enrollment.impl.CreateEnrollmentUseCaseImpl;
import nl.fontys.lms.domain.enrollment.CreateEnrollmentRequest;
import nl.fontys.lms.domain.enrollment.CreateEnrollmentResponse;
import nl.fontys.lms.persistence.CourseRepository;
import nl.fontys.lms.persistence.EnrollmentRepository;
import nl.fontys.lms.persistence.entity.CourseEntity;
import nl.fontys.lms.persistence.entity.EnrollmentEntity;
import nl.fontys.lms.persistence.entity.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateEnrollmentUseCaseImplTest {
    @Mock
    private EnrollmentRepository enrollmentRepository;

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private CreateEnrollmentUseCaseImpl createEnrollmentUseCase;


    @BeforeEach
    void setUp() {
        // Initialize or mock the enrollmentRepository and courseRepository
        this.createEnrollmentUseCase = new CreateEnrollmentUseCaseImpl(enrollmentRepository, courseRepository);
    }


    @Test
    void createEnrollment_Success() {
        // Arrange
        CreateEnrollmentRequest request = new CreateEnrollmentRequest(1L, 2L);
        when(enrollmentRepository.isStudentEnrolledInCourse(request.getStudentId(), request.getCourseId())).thenReturn(false);
        when(courseRepository.getCourseCapacity(request.getCourseId())).thenReturn(10);
        when(enrollmentRepository.getEnrollmentCountForCourse(request.getCourseId())).thenReturn(5);
        when(enrollmentRepository.save(any(EnrollmentEntity.class))).thenReturn(createMockEnrollmentEntity());

        // Act
        CreateEnrollmentResponse response = createEnrollmentUseCase.createEnrollment(request);

        // Assert
        assertNotNull(response);
        assertNull(response.getErrorMessages());
        assertNotNull(response.getEnrollmentId());
        assertNotNull(response.getEnrollmentId(), "Enrollment ID should not be null");
    }

//    @Test
//    void createEnrollment_StudentAlreadyEnrolled() {
//        // Arrange
//        CreateEnrollmentRequest request = new CreateEnrollmentRequest(1L, 2L);
//
//        // Set up mock behavior
//        when(enrollmentRepository.isStudentEnrolledInCourse(request.getStudentId(), request.getCourseId())).thenReturn(true);
//
//        // Use specific argument matcher for the courseRepository
//        when(courseRepository.getCourseCapacity(eq(1L))).thenReturn(42);
//
//        // Act
//        CreateEnrollmentResponse response = createEnrollmentUseCase.createEnrollment(request);
//
//        // Assert
//        // ... your assertions ...
//
//        // Verify method invocations
//        verify(enrollmentRepository, times(1)).isStudentEnrolledInCourse(anyLong(), anyLong());
//        verify(courseRepository, times(1)).getCourseCapacity(eq(1L));  // Ensure this method is called
//        verify(enrollmentRepository, times(1)).getEnrollmentCountForCourse(anyLong());
//        verify(enrollmentRepository, times(0)).save(any(EnrollmentEntity.class));  // Ensure this method is not called
//    }
//
//
//    @Test
//    void createEnrollment_CourseAtFullCapacity() {
//        // Arrange
//        CreateEnrollmentRequest request = new CreateEnrollmentRequest(1L, 2L);
//        when(enrollmentRepository.isStudentEnrolledInCourse(request.getStudentId(), request.getCourseId())).thenReturn(false);
//        when(courseRepository.getCourseCapacity(request.getCourseId())).thenReturn(5);
//        when(enrollmentRepository.getEnrollmentCountForCourse(request.getCourseId())).thenReturn(5);
//
//        // Act
//        CreateEnrollmentResponse response = createEnrollmentUseCase.createEnrollment(request);
//
//        // Assert
//        assertNotNull(response);
//        assertNotNull(response.getErrorMessages());
//        assertTrue(response.getErrorMessages().contains("Course is at full capacity"));
//        assertNull(response.getEnrollmentId());
//
//        // Example verification
//        verify(enrollmentRepository, times(1)).isStudentEnrolledInCourse(anyLong(), anyLong());
//        verify(courseRepository, times(1)).getCourseCapacity(anyLong());
//        verify(enrollmentRepository, times(1)).getEnrollmentCountForCourse(anyLong());
//        verify(enrollmentRepository, times(1)).save(any(EnrollmentEntity.class));
//
//    }

    private EnrollmentEntity createMockEnrollmentEntity() {
        return EnrollmentEntity.builder()
                .id(1L)
                .course(new CourseEntity())
                .student(new UserEntity())
                .enrollmentDate(new Date())
                .build();
    }
}
