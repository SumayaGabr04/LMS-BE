package nl.fontys.lms.business.user;

import nl.fontys.lms.domain.user.User;

import java.util.Optional;

public interface GetUserUseCase {
    Optional<User> getUserById(Long userId);
}
