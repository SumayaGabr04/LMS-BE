package nl.fontys.lms.business.course;

import nl.fontys.lms.domain.course.TopCoursesResponse;

import java.util.List;

public interface TopCoursesUseCase {
    TopCoursesResponse getTop3CoursesWithMostEnrolledStudents();
}
