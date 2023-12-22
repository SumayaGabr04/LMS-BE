package nl.fontys.lms.business.user;

import nl.fontys.lms.domain.user.GetUserRequest;
import nl.fontys.lms.domain.user.GetUserResponse;


public interface GetUserUseCase {
    GetUserResponse getUserById(GetUserRequest request);
}
