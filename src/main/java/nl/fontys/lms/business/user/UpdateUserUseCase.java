package nl.fontys.lms.business.user;

import nl.fontys.lms.domain.user.UpdateUserRequest;

public interface UpdateUserUseCase {
    void updateUser(UpdateUserRequest request);
}
