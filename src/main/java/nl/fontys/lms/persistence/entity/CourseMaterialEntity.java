package nl.fontys.lms.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "course_materials")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseMaterialEntity {
    @ToString.Exclude
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private CourseEntity course;

    @Lob // Use @Lob annotation for large binary data
    @Column(name = "material", columnDefinition = "LONGBLOB") // Specify the column as LONGBLOB
    private byte[] material;
}
