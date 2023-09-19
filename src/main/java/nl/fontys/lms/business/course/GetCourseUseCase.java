package nl.fontys.lms.business.course;

import nl.fontys.lms.domain.course.Course;

import java.util.Optional;

public interface GetCourseUseCase {
    Optional<Course>  getCourse(long courseId);
}
