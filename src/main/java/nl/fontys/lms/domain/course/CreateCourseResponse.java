package nl.fontys.lms.domain.course;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;

@Data
@Builder
public class CreateCourseResponse {
    private Long courseId;
    private ArrayList<String> errorMessages;
}
