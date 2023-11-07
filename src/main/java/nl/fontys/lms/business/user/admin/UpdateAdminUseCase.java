package nl.fontys.lms.business.user.admin;

import nl.fontys.lms.domain.user.UpdateUserRequest;
import nl.fontys.lms.domain.user.admin.UpdateAdminRequest;

public interface UpdateAdminUseCase {
    void updateAdmin(UpdateUserRequest request);
}
