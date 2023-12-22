package nl.fontys.lms.business.enrollment;

import nl.fontys.lms.domain.enrollment.GetEnrollmentsForStudentResponse;

public interface GetEnrollmentsForStudentUseCase {
    public GetEnrollmentsForStudentResponse getEnrollmentsForStudent(Long studentId);
}
