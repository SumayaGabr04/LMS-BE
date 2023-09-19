package nl.fontys.lms.domain.user.student;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.lms.domain.user.UserRequest;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateStudentRequest {
    private Long id; // ID of the student to update
    private UserRequest user;
    private String major;
    private String enrollmentDate;
}
