package nl.fontys.lms.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "enrollment")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentEntity {
    @ToString.Exclude
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private CourseEntity course;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity student;

    @Temporal(TemporalType.DATE)
    @Column(name = "enrollment_date")
    private Date enrollmentDate;
}
