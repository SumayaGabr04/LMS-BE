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
//@EqualsAndHashCode(callSuper = true)
public class Student extends User{
    private ArrayList<Course> coursesEnrolled;
    private String major;
    private String enrollmentDate;

//    @Builder // Create a builder for the Student class
//    public Student(Long id, String firstName, String lastName, String email, String password, ArrayList<Course> coursesEnrolled, String major, String enrollmentDate) {
//        super(id, firstName, lastName, email, password);
//        this.coursesEnrolled = coursesEnrolled;
//        this.major = major;
//        this.enrollmentDate = enrollmentDate;
//    }
}
