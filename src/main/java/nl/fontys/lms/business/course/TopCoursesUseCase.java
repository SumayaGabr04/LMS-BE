package nl.fontys.lms.business.course;

import nl.fontys.lms.domain.course.TopCoursesResponse;


public interface TopCoursesUseCase {
    TopCoursesResponse getTop3CoursesWithMostEnrolledStudents();
}
