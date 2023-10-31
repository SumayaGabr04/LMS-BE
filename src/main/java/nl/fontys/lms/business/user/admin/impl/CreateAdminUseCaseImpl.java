package nl.fontys.lms.business.user.admin.impl;

import lombok.AllArgsConstructor;
import nl.fontys.lms.business.exception.EmailAlreadyExists;
import nl.fontys.lms.business.user.admin.CreateAdminUseCase;
import nl.fontys.lms.domain.user.CreateResponse;
import nl.fontys.lms.domain.user.CreateUserRequest;
import nl.fontys.lms.domain.user.admin.CreateAdminRequest;
import nl.fontys.lms.persistence.AdminRepository;
import nl.fontys.lms.persistence.entity.AdminEntity;
import org.springframework.stereotype.Service;

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
                .id(adminEntity.getAdminId())
                .build();
    }

    private AdminEntity saveNewAdmin(CreateAdminRequest request) {
        AdminEntity newAdmin = AdminEntity.builder()
                .firstName(request.getUser().getFirstName())
                .lastName(request.getUser().getLastName())
                .email(request.getUser().getEmail())
                .password(request.getUser().getPassword())
                .department(request.getDepartment())
                .hireDate(request.getHireDate())
                .build();

        return adminRepository.save(newAdmin);
    }
}
