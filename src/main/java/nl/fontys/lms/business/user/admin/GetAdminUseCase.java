package nl.fontys.lms.business.user.admin;

import nl.fontys.lms.domain.user.admin.Admin;
import nl.fontys.lms.domain.user.admin.GetAdminResponse;

import java.util.Optional;

public interface GetAdminUseCase {
    Optional<GetAdminResponse> getAdmin(Long adminId);
}
