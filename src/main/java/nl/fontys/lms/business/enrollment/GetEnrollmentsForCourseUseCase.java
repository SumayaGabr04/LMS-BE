package nl.fontys.lms.business.enrollment;

import nl.fontys.lms.domain.Enrollment.GetEnrollmentsForCourseResponse;

public interface GetEnrollmentsForCourseUseCase {
    public GetEnrollmentsForCourseResponse getEnrollmentsForCourse(Long courseId);
}
