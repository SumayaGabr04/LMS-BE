package nl.fontys.lms.domain.course;

import jakarta.validation.constraints.*;
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
    @Size(min = 3, max = 100, message = "Course name must be between 3 and 100 characters.")
    @Pattern(regexp = "^[a-zA-Z0-9\\s]+$", message = "Course name can only contain letters, numbers, and spaces.")
    private String courseName;

    @NotBlank
    private String description;

    @NotBlank
    private String instructor;

    @NotNull
    private int enrollmentCapacity;

    @NotNull
    @FutureOrPresent(message = "Start date must be in the future or the present.")
    private Date startDate;

    @NotNull
    @FutureOrPresent(message = "End date must be in the future or the present.")
    private Date endDate;
}
