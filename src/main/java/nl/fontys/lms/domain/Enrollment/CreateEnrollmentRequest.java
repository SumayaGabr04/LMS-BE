package nl.fontys.lms.domain.Enrollment;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateEnrollmentRequest {
    @NotNull
    private Long courseId;

    @NotNull
    private Long studentId;
}
