package nl.fontys.lms.business.course;

import nl.fontys.lms.domain.course.UpdateCourseRequest;

public interface UpdateCourseUseCase {
    void UpdateCourse(UpdateCourseRequest request);
}
