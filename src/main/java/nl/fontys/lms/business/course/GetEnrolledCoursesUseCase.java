package nl.fontys.lms.business.course;

import nl.fontys.lms.domain.course.GetAllEnrolledCoursesRequest;
import nl.fontys.lms.domain.course.GetAllEnrolledCoursesResponse;

public interface GetEnrolledCoursesUseCase {
    GetAllEnrolledCoursesResponse getCourses(GetAllEnrolledCoursesRequest request);
}
