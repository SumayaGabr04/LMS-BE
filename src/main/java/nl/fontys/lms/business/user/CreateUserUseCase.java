package nl.fontys.lms.business.user;

import nl.fontys.lms.domain.user.CreateResponse;
import nl.fontys.lms.domain.user.UserRequest;

public interface CreateUserUseCase {
    CreateResponse createUser(UserRequest request);
}
