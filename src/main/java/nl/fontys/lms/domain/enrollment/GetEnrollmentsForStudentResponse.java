package nl.fontys.lms.domain.enrollment;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GetEnrollmentsForStudentResponse {
    private Long studentId;
    private List<Enrollment> enrollments;
}
