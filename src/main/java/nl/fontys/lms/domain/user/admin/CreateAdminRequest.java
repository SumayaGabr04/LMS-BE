package nl.fontys.lms.domain.user.admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.lms.domain.user.CreateUserRequest;

@Data
@Builder(builderMethodName = "adminBuilder")
@AllArgsConstructor
@NoArgsConstructor
public class CreateAdminRequest extends CreateUserRequest{
    private CreateUserRequest user;
    private String department;
    private String hireDate;

    public static CreateAdminRequest fromUserRequestAndDepartment(CreateUserRequest userRequest, String department) {
        return CreateAdminRequest.adminBuilder()
                .user(userRequest)
                .department(department)
                .build();
    }
}
