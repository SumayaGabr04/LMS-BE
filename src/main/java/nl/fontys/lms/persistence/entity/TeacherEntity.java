package nl.fontys.lms.persistence.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import nl.fontys.lms.domain.course.Course;

import java.util.ArrayList;
import java.util.Date;

@Entity
@Table(name = "teacher")
@Data
@SuperBuilder
@NoArgsConstructor
public class TeacherEntity extends UserEntity{
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    private Long teacherId;

//    @ManyToMany(mappedBy = "teachers")
//    private ArrayList<CourseEntity> coursesTaught;

    @Column(name = "department")
    private String department;

    @Temporal(TemporalType.DATE)
    @Column(name = "hire_date")
    private Date hireDate;

    @OneToOne
    @MapsId
    private UserEntity user;
}
