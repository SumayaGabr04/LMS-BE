package nl.fontys.lms.business.user.admin;

import nl.fontys.lms.domain.user.CreateResponse;
import nl.fontys.lms.domain.user.CreateUserRequest;

public interface CreateAdminUseCase {
    CreateResponse createAdmin(CreateUserRequest request);
}
