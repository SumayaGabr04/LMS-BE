package nl.fontys.lms.domain.user.student;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import nl.fontys.lms.domain.user.User;
import nl.fontys.lms.domain.user.UserResponse;

import java.util.Optional;

@Data
@Builder
@AllArgsConstructor
public class GetStudentResponse implements UserResponse {
    private final Optional<Student> student;

    @Override
    public Optional<User> getUser() {
        // Map the Student object to a User object or return an empty Optional
        return student.map(student -> {
            // Map Student properties to User properties here
            return new User(
                    student.getId(),
                    "student", // Assuming the role for students is "student"
                    student.getFirstName(),
                    student.getLastName(),
                    student.getEmail(),
                    student.getPasswordHash(),
                    student.getPasswordSalt()
            );
        });
    }
}
