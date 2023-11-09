package nl.fontys.lms.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "course")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseEntity {
    @ToString.Exclude
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Length(min = 2, max = 50)
    @Column(name = "course_name")
    private String courseName;
    @NotBlank
    @Length(min = 2, max = 200)
    @Column(name = "description")
    private String description;
    @NotBlank
    @Length(min = 2, max = 50)
    @Column(name = "instructor")
    private String instructor;
    @NotNull
    @Column(name = "enrollment_capacity")
    private int enrollmentCapacity;

    @Temporal(TemporalType.DATE) // Define date format
    @Column(name = "start_date")
    private Date startDate;
    @Temporal(TemporalType.DATE) // Define date format
    @Column(name = "end_date")
    private Date endDate;

    @ManyToMany
    @JoinTable(name = "course_materials", joinColumns = @JoinColumn(name = "course_id"), inverseJoinColumns = @JoinColumn(name = "material_id"))
    private List<CourseMaterialEntity> courseMaterials;


    @ManyToMany
    @JoinTable(name = "course_student",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private List<StudentEntity> enrolledStudents;
}
