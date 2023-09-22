package nl.fontys.lms.persistence.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;

@Data
@SuperBuilder
@NoArgsConstructor
public class StudentEntity extends UserEntity{
    private Long studentId;

    private ArrayList<CourseEntity> coursesEnrolled;

    private String major;
    private String enrollmentDate;
}
