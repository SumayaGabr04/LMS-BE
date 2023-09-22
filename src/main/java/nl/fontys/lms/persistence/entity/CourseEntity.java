package nl.fontys.lms.persistence.entity;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Builder
public class CourseEntity {
    private Long id;

    private String courseName;
    private String description;
    private String instructor;
    private int enrollmentCapacity;
    private Date startDate;
    private Date endDate;

    private ArrayList<String> courseMaterials;
}
