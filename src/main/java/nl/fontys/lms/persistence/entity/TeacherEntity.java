package nl.fontys.lms.persistence.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;

@Data
@SuperBuilder
@NoArgsConstructor
public class TeacherEntity extends UserEntity{
    private Long teacherId;

    private ArrayList<CourseEntity> coursesTaught;

    private String department;
    private String hireDate;
}
