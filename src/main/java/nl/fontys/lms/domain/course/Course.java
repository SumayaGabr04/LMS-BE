package nl.fontys.lms.domain.course;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.lms.domain.material.Material;
import nl.fontys.lms.domain.user.User;

import java.util.ArrayList;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    private Long id;
    private String courseName;
    private String description;
    private String instructor;
    private int enrollmentCapacity;
    private Date startDate;
    private Date endDate;
    private ArrayList<Material> courseMaterials;
    private ArrayList<User> enrolledStudents;
}
