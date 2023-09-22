package nl.fontys.lms.domain.user.teacher;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.lms.domain.user.UserRequest;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateTeacherRequest {
    private UserRequest user;
    private String department;
    private String hireDate;
}