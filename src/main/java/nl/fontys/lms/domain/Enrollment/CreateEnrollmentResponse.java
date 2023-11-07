package nl.fontys.lms.domain.Enrollment;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;

@Data
@Builder
public class CreateEnrollmentResponse {
    private Long enrollmentId;
    private ArrayList<String> errorMessages;
}
