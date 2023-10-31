package nl.fontys.lms.business.user;

import nl.fontys.lms.domain.user.CreateResponse;
import nl.fontys.lms.domain.user.CreateUserRequest;

public interface CreateUserUseCase {
    CreateResponse createUser(CreateUserRequest request);
}
