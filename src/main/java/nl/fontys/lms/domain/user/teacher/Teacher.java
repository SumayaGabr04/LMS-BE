package nl.fontys.lms.domain.user.teacher;

import lombok.*;
import lombok.experimental.SuperBuilder;
import nl.fontys.lms.domain.course.Course;
import nl.fontys.lms.domain.user.User;

import java.util.ArrayList;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Teacher extends User{
    private ArrayList<Course> coursesTaught;
    private String department;
    private String hireDate;
}
