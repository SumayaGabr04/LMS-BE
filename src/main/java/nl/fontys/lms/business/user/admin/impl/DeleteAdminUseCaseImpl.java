package nl.fontys.lms.business.user.admin.impl;

import lombok.AllArgsConstructor;
import nl.fontys.lms.business.exception.UserNotFoundException;
import nl.fontys.lms.business.user.admin.DeleteAdminUseCase;
import nl.fontys.lms.persistence.AdminRepository;
import nl.fontys.lms.persistence.entity.AdminEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteAdminUseCaseImpl implements DeleteAdminUseCase {
    private final AdminRepository adminRepository;

    @Override
    public void deleteAdmin(Long adminId) {
        AdminEntity existingAdmin = adminRepository.findById(adminId)
                .orElseThrow(UserNotFoundException::new);

        adminRepository.deleteById(adminId);
    }
}
