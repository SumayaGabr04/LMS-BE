package nl.fontys.lms.business.course;

import nl.fontys.lms.domain.course.GetAllCoursesResponse;

public interface GetAllCoursesUseCase {
    GetAllCoursesResponse getCourses();
    GetAllCoursesResponse searchCourses(String searchTerm);
}
