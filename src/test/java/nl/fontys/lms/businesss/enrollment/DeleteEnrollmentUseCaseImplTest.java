package nl.fontys.lms.businesss.enrollment;

import nl.fontys.lms.business.enrollment.impl.DeleteEnrollmentUseCaseImpl;
import nl.fontys.lms.persistence.EnrollmentRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.verify;

@SpringBootTest
public class DeleteEnrollmentUseCaseImplTest {
    @Mock
    private EnrollmentRepository enrollmentRepository;

    @InjectMocks
    private DeleteEnrollmentUseCaseImpl deleteEnrollmentUseCase;

    @Test
    void deleteEnrollment_shouldInvokeRepositoryDeleteById() {
        // Given
        Long enrollmentId = 1L;

        // When
        deleteEnrollmentUseCase.deleteEnrollment(enrollmentId);

        // Then
        verify(enrollmentRepository).deleteById(enrollmentId);
    }
}
