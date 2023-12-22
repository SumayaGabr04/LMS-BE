package nl.fontys.lms.business.enrollment;

import nl.fontys.lms.domain.enrollment.GetEnrollmentsForCourseResponse;

public interface GetEnrollmentsForCourseUseCase {
    public GetEnrollmentsForCourseResponse getEnrollmentsForCourse(Long courseId);
}
