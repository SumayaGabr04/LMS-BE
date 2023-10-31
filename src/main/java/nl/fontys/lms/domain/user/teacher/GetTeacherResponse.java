package nl.fontys.lms.domain.user.teacher;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import nl.fontys.lms.domain.user.UserResponse;
import nl.fontys.lms.domain.user.User;

import java.util.Optional;

@Data
@Builder
@AllArgsConstructor
public class GetTeacherResponse implements UserResponse {
    private final Optional<Teacher> teacher;

    @Override
    public Optional<User> getUser() {
        // Map the Teacher object to a User object or return an empty Optional
        return teacher.map(teacher -> {
            // Map Teacher properties to User properties here
            return new User(
                    teacher.getId(),
                    "teacher",
                    teacher.getFirstName(),
                    teacher.getLastName(),
                    teacher.getEmail(),
                    teacher.getPassword()
            );
        });
    }
}
