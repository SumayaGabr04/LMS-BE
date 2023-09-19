package nl.fontys.lms.domain.course;

import lombok.Builder;
import lombok.Data;
import nl.fontys.lms.domain.course.Course;


import java.util.ArrayList;

@Data
@Builder
public class GetAllCoursesResponse {
    private ArrayList<Course> courses;
}
