package nl.fontys.lms.domain.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.lms.business.user.UserRole;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {
    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    @Email(message = "Please provide a valid email address")
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private UserRole role;

    // Additional fields for students
    private String major;

    // Additional fields for teachers
    private String department;
    private Date hireDate;
}
