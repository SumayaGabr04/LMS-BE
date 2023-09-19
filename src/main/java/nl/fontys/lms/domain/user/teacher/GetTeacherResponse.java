package nl.fontys.lms.domain.user.teacher;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.lms.domain.course.Course;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetTeacherResponse {
//    private Teacher teacher;
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String department;
    private String hireDate;
    private List<Course> coursesTaught;
}
