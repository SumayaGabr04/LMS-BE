package nl.fontys.lms.domain.user.student;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.lms.domain.user.CreateUserRequest;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateStudentRequest {
    private CreateUserRequest user;
    private String major;
    private String enrollmentDate;
}
