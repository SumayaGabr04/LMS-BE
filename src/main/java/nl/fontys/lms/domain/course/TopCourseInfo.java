package nl.fontys.lms.domain.course;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TopCourseInfo {
    private String courseName;
    private long totalStudents;
}
