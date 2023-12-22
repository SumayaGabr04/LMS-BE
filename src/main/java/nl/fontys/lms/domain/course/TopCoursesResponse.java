package nl.fontys.lms.domain.course;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class TopCoursesResponse {
    private List<TopCourseInfo> topCourses;
}
