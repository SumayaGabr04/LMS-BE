package nl.fontys.lms.domain.Enrollment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Enrollment {

    private Long courseId;
    private Long studentId;
    private Date enrollmentDate;
}
