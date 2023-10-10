package nl.fontys.lms.business.user.teacher;

import nl.fontys.lms.domain.user.teacher.TeacherCoursesRequest;
import nl.fontys.lms.domain.user.teacher.TeacherCoursesResponse;

import java.util.Optional;

public interface TeacherCourseUseCase {
    Optional<TeacherCoursesResponse> getTeacherCourses(long teacherId);
}
