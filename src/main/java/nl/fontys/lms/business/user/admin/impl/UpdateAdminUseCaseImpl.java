package nl.fontys.lms.business.user.admin.impl;

import lombok.AllArgsConstructor;
import nl.fontys.lms.business.exception.UserNotFoundException;
import nl.fontys.lms.business.user.UpdateUserUseCase;
import nl.fontys.lms.business.user.admin.UpdateAdminUseCase;
import nl.fontys.lms.domain.user.UpdateUserRequest;
import nl.fontys.lms.domain.user.admin.UpdateAdminRequest;
import nl.fontys.lms.persistence.AdminRepository;
import nl.fontys.lms.persistence.entity.AdminEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateAdminUseCaseImpl implements UpdateAdminUseCase {
    private final AdminRepository adminRepository;

    @Override
    public void updateAdmin(UpdateUserRequest request) {
        AdminEntity existingAdmin = adminRepository.findById(request.getId());
        if (existingAdmin == null) {
            throw new UserNotFoundException();
        }

        // Map UpdateUserRequest to UpdateAdminRequest
        UpdateAdminRequest adminRequest = new UpdateAdminRequest();
        adminRequest.setUser(request);

        // Update admin fields as needed
        existingAdmin.setFirstName(adminRequest.getUser().getFirstName());
        existingAdmin.setLastName(adminRequest.getUser().getLastName());
        existingAdmin.setEmail(adminRequest.getUser().getEmail());
        existingAdmin.setPassword(adminRequest.getUser().getPassword());
        existingAdmin.setDepartment(adminRequest.getDepartment());
        existingAdmin.setHireDate(adminRequest.getHireDate());

        adminRepository.save(existingAdmin);
    }
}
