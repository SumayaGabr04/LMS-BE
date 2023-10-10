package nl.fontys.lms.domain.user.teacher;

import lombok.Builder;
import lombok.Data;
import nl.fontys.lms.domain.course.Course;
import nl.fontys.lms.persistence.entity.CourseEntity;

import java.util.ArrayList;

@Data
@Builder
public class TeacherCoursesResponse {
    private ArrayList<Course> coursesTaught;
    private String message;
}
