package nl.fontys.lms.domain.user.student;

import lombok.*;
import lombok.experimental.SuperBuilder;
import nl.fontys.lms.domain.course.Course;
import nl.fontys.lms.domain.user.User;

import java.util.ArrayList;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Student extends User{
    private ArrayList<Course> coursesEnrolled;
    private String major;
    private String enrollmentDate;

}
