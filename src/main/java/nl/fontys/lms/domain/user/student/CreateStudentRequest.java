package nl.fontys.lms.domain.user.student;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.lms.domain.user.CreateUserRequest;

@Data
@Builder(builderMethodName = "studentBuilderMethod")
@AllArgsConstructor
@NoArgsConstructor
public class CreateStudentRequest extends CreateUserRequest{
    private CreateUserRequest user;
    private String major;

    public static CreateStudentRequest fromUserRequestAndMajor(CreateUserRequest userRequest, String major) {
        return CreateStudentRequest.studentBuilderMethod()
                .user(CreateUserRequest.builder()
                        .firstName(userRequest.getFirstName())
                        .lastName(userRequest.getLastName())
                        .email(userRequest.getEmail())
                        .password(userRequest.getPassword())
                        .role(userRequest.getRole())
                        .build())
                .major(major)
                .build();
    }
}
