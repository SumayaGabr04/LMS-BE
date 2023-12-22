package nl.fontys.lms.business.enrollment;

import nl.fontys.lms.domain.enrollment.CreateEnrollmentRequest;
import nl.fontys.lms.domain.enrollment.CreateEnrollmentResponse;

public interface CreateEnrollmentUseCase {
    public CreateEnrollmentResponse createEnrollment(CreateEnrollmentRequest request);
}
