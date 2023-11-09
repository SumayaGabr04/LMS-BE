package nl.fontys.lms.business.user.admin.impl;

import lombok.AllArgsConstructor;
import nl.fontys.lms.business.exception.EmailAlreadyExists;
import nl.fontys.lms.business.user.UserRole;
import nl.fontys.lms.business.user.UserRoleUtil;
import nl.fontys.lms.business.user.admin.CreateAdminUseCase;
import nl.fontys.lms.domain.user.CreateResponse;
import nl.fontys.lms.domain.user.CreateUserRequest;
import nl.fontys.lms.domain.user.admin.CreateAdminRequest;
import nl.fontys.lms.persistence.AdminRepository;
import nl.fontys.lms.persistence.entity.AdminEntity;
import nl.fontys.lms.security.PasswordUtils;
import nl.fontys.lms.security.SaltUtils;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@AllArgsConstructor
public class CreateAdminUseCaseImpl implements CreateAdminUseCase {
    private final AdminRepository adminRepository;

    @Override
    public CreateResponse createAdmin(CreateUserRequest request) {
        // Map CreateUserRequest to CreateAdminRequest
        CreateAdminRequest adminRequest = new CreateAdminRequest();
        adminRequest.setUser(request);

        if (adminRepository.existsByEmail(adminRequest.getUser().getEmail())) {
            throw new EmailAlreadyExists();
        }
        AdminEntity adminEntity = saveNewAdmin(adminRequest);

        return CreateResponse.builder()
                .id(adminEntity.getUserId())
                .build();
    }

    private AdminEntity saveNewAdmin(CreateAdminRequest request) {
        // Generate a random salt
        String salt = SaltUtils.generateSalt();

        // Hash the plain password with the generated salt
        String hashedPassword = PasswordUtils.hashPassword(request.getUser().getPassword(), salt);

        String role = UserRoleUtil.userRoleToString(UserRole.ADMIN);

        return adminRepository.save(AdminEntity.builder()
                .firstName(request.getUser().getFirstName())
                .lastName(request.getUser().getLastName())
                .email(request.getUser().getEmail())
                .passwordHash(hashedPassword)
                .passwordSalt(salt)
                .department(request.getDepartment())
                .hireDate(parseDate(request.getHireDate()))
                .role(role)
                .build());
    }

    private Date parseDate(String dateStr) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Update the format as needed
            return dateFormat.parse(dateStr);
        } catch (ParseException e) {
            // Handle the parsing exception
            e.printStackTrace();
            return null; // or throw an exception
        }
    }
}
