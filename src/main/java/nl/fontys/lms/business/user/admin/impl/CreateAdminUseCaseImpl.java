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
        AdminEntity newAdmin = AdminEntity.builder()
                .firstName(request.getUser().getFirstName())
                .lastName(request.getUser().getLastName())
                .email(request.getUser().getEmail())
                .passwordHash(request.getUser().getPassword())
                .department(request.getDepartment())
                .hireDate(parseDate(request.getHireDate()))
                .build();

        return adminRepository.save(newAdmin);
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
