package nl.fontys.lms.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "student")
@Data
@SuperBuilder
@NoArgsConstructor
public class StudentEntity extends UserEntity{
//    @ToString.Exclude
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    private Long studentId;

    @ManyToMany(mappedBy = "enrolledStudents")
    private List<CourseEntity> coursesEnrolled;

    @NotBlank
    @Length(min = 2, max = 50)
    @Column(name = "major")
    private String major;

    @OneToOne
    @MapsId
    private UserEntity user;

}
