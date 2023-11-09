package nl.fontys.lms.domain.user.teacher;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.lms.domain.user.CreateUserRequest;

@Data
@Builder(builderMethodName = "teacherBuilder")
@AllArgsConstructor
@NoArgsConstructor
public class CreateTeacherRequest extends CreateUserRequest{
    private CreateUserRequest user;
    private String department;
    private String hireDate;
    public static CreateTeacherRequest fromUserRequestAndDepartment(CreateUserRequest userRequest, String department) {
        return CreateTeacherRequest.teacherBuilder()
                .user(userRequest)
                .department(department)
                .build();
    }
}
