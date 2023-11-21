package nl.fontys.lms.business.user;

import nl.fontys.lms.domain.user.CreateUserResponse;
import nl.fontys.lms.domain.user.CreateUserRequest;

public interface CreateUserUseCase {
    CreateUserResponse createUser(CreateUserRequest request);
}
