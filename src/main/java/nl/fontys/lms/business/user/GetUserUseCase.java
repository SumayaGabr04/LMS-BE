package nl.fontys.lms.business.user;

import nl.fontys.lms.domain.user.GetUserRequest;
import nl.fontys.lms.domain.user.GetUserResponse;
import nl.fontys.lms.domain.user.User;

import java.util.Optional;

public interface GetUserUseCase {
    GetUserResponse getUserById(GetUserRequest request);
}
