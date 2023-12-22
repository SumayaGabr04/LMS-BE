package nl.fontys.lms.businesss.enrollment;

import nl.fontys.lms.business.enrollment.impl.EnrollmentConverter;
import nl.fontys.lms.business.enrollment.impl.GetEnrollmentsForStudentUseCaseImpl;
import nl.fontys.lms.domain.enrollment.Enrollment;
import nl.fontys.lms.domain.enrollment.GetEnrollmentsForStudentResponse;
import nl.fontys.lms.persistence.EnrollmentRepository;
import nl.fontys.lms.persistence.entity.CourseEntity;
import nl.fontys.lms.persistence.entity.EnrollmentEntity;
import nl.fontys.lms.persistence.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GetEnrollmentsForStudentUseCaseImplTest {
    @Mock
    private EnrollmentRepository enrollmentRepository;

    @InjectMocks
    private GetEnrollmentsForStudentUseCaseImpl getEnrollmentsForStudentUseCase;

    @Test
    void testGetEnrollmentsForStudent() {
        // Arrange
        Long studentId = 1L;

        EnrollmentEntity enrollmentEntity1 = new EnrollmentEntity(1L, new CourseEntity(), new UserEntity(), new Date());
        EnrollmentEntity enrollmentEntity2 = new EnrollmentEntity(2L, new CourseEntity(), new UserEntity(), new Date());

        List<EnrollmentEntity> mockEnrollmentEntities = Arrays.asList(enrollmentEntity1, enrollmentEntity2);

        // Correct way to mock findAllEnrollmentsForStudent
        when(enrollmentRepository.findAllEnrollmentsForStudent(studentId)).thenReturn(mockEnrollmentEntities);

        // Act
        GetEnrollmentsForStudentResponse response = getEnrollmentsForStudentUseCase.getEnrollmentsForStudent(studentId);

        // Assert
        List<Enrollment> expectedEnrollments = mockEnrollmentEntities.stream()
                .map(EnrollmentConverter::convert)
                .collect(Collectors.toList());

        assertEquals(studentId, response.getStudentId());
        assertEquals(expectedEnrollments, response.getEnrollments());
        verify(enrollmentRepository, times(1)).findAllEnrollmentsForStudent(studentId);
    }
}
