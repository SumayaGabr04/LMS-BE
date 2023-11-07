package nl.fontys.lms.domain.user;

import java.util.Optional;

public interface UserResponse {
    Optional<User> getUser();
}
