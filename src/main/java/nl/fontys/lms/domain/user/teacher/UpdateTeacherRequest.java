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
public class UpdateTeacherRequest {
    private Long id; // ID of the teacher to update
    private UserRequest user;
    private String department;
    private String hireDate;
}
