package nl.fontys.lms.domain.user.admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import nl.fontys.lms.domain.user.UserResponse;
import nl.fontys.lms.domain.user.User;

import java.util.Optional;

@Data
@Builder
@AllArgsConstructor
public class GetAdminResponse implements UserResponse {
    private final Optional<Admin> admin;

    @Override
    public Optional<User> getUser() {
        // Map the Admin object to a User object or return an empty Optional
        return admin.map(admin -> {
            // Map Admin properties to User properties here
            return new User(
                    admin.getId(),
                    "admin",
                    admin.getFirstName(),
                    admin.getLastName(),
                    admin.getEmail(),
                    admin.getPasswordHash(),
                    admin.getPasswordSalt()
            );
        });
    }
}
