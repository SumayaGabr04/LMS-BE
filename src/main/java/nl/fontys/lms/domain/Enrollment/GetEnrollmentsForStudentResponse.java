package nl.fontys.lms.domain.Enrollment;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GetEnrollmentsForStudentResponse {
    private Long studentId;
    private List<Enrollment> enrollments;
}
