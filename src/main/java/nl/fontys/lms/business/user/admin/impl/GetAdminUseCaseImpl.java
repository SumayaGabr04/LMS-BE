package nl.fontys.lms.business.user.admin.impl;

import lombok.AllArgsConstructor;
import nl.fontys.lms.business.exception.UserNotFoundException;
import nl.fontys.lms.business.user.admin.GetAdminUseCase;
import nl.fontys.lms.business.user.teacher.impl.TeacherConverter;
import nl.fontys.lms.domain.user.admin.Admin;
import nl.fontys.lms.domain.user.admin.GetAdminResponse;
import nl.fontys.lms.domain.user.teacher.Teacher;
import nl.fontys.lms.persistence.AdminRepository;
import nl.fontys.lms.persistence.entity.AdminEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GetAdminUseCaseImpl implements GetAdminUseCase {
    private final AdminRepository adminRepository;

    @Override
    public Optional<GetAdminResponse> getAdmin(Long adminId) {
        AdminEntity adminEntity = adminRepository.findById(adminId);
        if (adminEntity == null) {
            throw new UserNotFoundException();
        }

        // Convert AdminEntity to GetAdminResponse using a converter
        Admin admin = AdminConverter.convert(adminEntity);
        GetAdminResponse response = new GetAdminResponse(Optional.of(admin));

        return Optional.of(response);
    }
}
