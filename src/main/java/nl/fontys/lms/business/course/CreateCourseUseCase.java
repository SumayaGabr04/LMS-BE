package nl.fontys.lms.business.course;

import nl.fontys.lms.domain.course.CreateCourseRequest;
import nl.fontys.lms.domain.course.CreateCourseResponse;

public interface CreateCourseUseCase {
CreateCourseResponse createCourse(CreateCourseRequest request);
}
