package nl.fontys.lms.persistence.entity;

import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class CourseEntity {
    private Long id;

    private String courseName;
    private String description;
    private String instructor;
    private int enrollmentCapacity;
    private Date startDate;
    private Date endDate;

    // type content
    private ArrayList<String> courseMaterials;
}
