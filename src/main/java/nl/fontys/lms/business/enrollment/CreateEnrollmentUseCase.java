package nl.fontys.lms.business.enrollment;

import nl.fontys.lms.domain.Enrollment.CreateEnrollmentRequest;
import nl.fontys.lms.domain.Enrollment.CreateEnrollmentResponse;

public interface CreateEnrollmentUseCase {
    public CreateEnrollmentResponse createEnrollment(CreateEnrollmentRequest request);
}
