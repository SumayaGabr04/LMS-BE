package nl.fontys.lms.business.course;

import java.util.List;

public interface TopCoursesUseCase {
    List<Object[]> getTop3CoursesWithMostEnrolledStudents();
}
