package nl.fontys.lms.domain.user.teacher;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.lms.domain.user.CreateUserRequest;
import nl.fontys.lms.domain.user.UpdateUserRequest;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTeacherRequest {
    private UpdateUserRequest user;
    private String department;
    private Date hireDate;
}
