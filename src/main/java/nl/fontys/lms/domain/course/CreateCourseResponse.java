package nl.fontys.lms.domain.course;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateCourseResponse {
    private long courseId;
}
