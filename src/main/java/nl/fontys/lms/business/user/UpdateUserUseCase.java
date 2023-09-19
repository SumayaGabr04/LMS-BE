package nl.fontys.lms.business.user;

import nl.fontys.lms.domain.user.UpdateUserRequest;
import nl.fontys.lms.domain.user.UserRequest;

public interface UpdateUserUseCase {
    void updateUser(UpdateUserRequest request);
}
