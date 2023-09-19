package nl.fontys.lms.domain.course;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateCourseRequest {

    @NotBlank
    private String courseName;

    @NotBlank
    private String description;

    @NotBlank
    private String instructor;

    @NotNull
    private int enrollmentCapacity;

    @NotNull
    private Date startDate;

    @NotNull
    private Date endDate;
}
