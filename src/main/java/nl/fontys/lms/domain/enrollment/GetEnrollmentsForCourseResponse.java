package nl.fontys.lms.domain.enrollment;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GetEnrollmentsForCourseResponse {
    private Long courseId;
    private List<Enrollment> enrollments;
}
