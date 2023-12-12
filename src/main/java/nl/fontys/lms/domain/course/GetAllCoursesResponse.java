package nl.fontys.lms.domain.course;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;

@Data
@Builder
public class GetAllCoursesResponse {
    private ArrayList<Course> courses;
}
