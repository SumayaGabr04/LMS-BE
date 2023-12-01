package nl.fontys.lms.domain.Enrollment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.lms.domain.course.Course;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Enrollment {
    private Long enrollmentId;
//    private Long courseId;
    private Course course;
    private Long studentId;
    private Date enrollmentDate;
}
