package nl.fontys.lms.business.enrollment;

import nl.fontys.lms.domain.Enrollment.GetEnrollmentsForStudentResponse;

public interface GetEnrollmentsForStudentUseCase {
    public GetEnrollmentsForStudentResponse getEnrollmentsForStudent(Long studentId);
}
