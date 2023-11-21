package nl.fontys.lms.business.user.admin.impl;

import lombok.AllArgsConstructor;
import nl.fontys.lms.business.exception.UserNotFoundException;
import nl.fontys.lms.business.user.admin.UpdateAdminUseCase;
import nl.fontys.lms.domain.user.UpdateUserRequest;
import nl.fontys.lms.domain.user.admin.UpdateAdminRequest;
import nl.fontys.lms.persistence.AdminRepository;
import nl.fontys.lms.persistence.entity.AdminEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UpdateAdminUseCaseImpl implements UpdateAdminUseCase {
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void updateAdmin(UpdateUserRequest request) {
        Optional<AdminEntity> existingAdminOptional = adminRepository.findById(request.getId());

        if (existingAdminOptional.isPresent()) {
            AdminEntity existingAdmin = existingAdminOptional.get();

            // Map UpdateUserRequest to UpdateAdminRequest
            UpdateAdminRequest adminRequest = new UpdateAdminRequest();
            adminRequest.setUser(request);

            // Update admin fields as needed
            existingAdmin.setFirstName(adminRequest.getUser().getFirstName());
            existingAdmin.setLastName(adminRequest.getUser().getLastName());
            existingAdmin.setEmail(adminRequest.getUser().getEmail());

            // Hash and salt the new password
            String hashedPassword = passwordEncoder.encode(request.getPassword());

            existingAdmin.setPasswordHash(hashedPassword);
            existingAdmin.setPasswordSalt(null);  // Assuming no salt is used for encoding in PasswordEncoderConfig

            existingAdmin.setDepartment(adminRequest.getDepartment());
            existingAdmin.setHireDate(adminRequest.getHireDate());

            adminRepository.save(existingAdmin);
        } else {
            throw new UserNotFoundException();
        }
    }
}
