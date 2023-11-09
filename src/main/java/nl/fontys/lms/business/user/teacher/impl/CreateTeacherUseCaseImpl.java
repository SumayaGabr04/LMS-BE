package nl.fontys.lms.business.user.teacher.impl;

import lombok.AllArgsConstructor;
import nl.fontys.lms.business.exception.EmailAlreadyExists;
import nl.fontys.lms.business.user.UserRole;
import nl.fontys.lms.business.user.UserRoleUtil;
import nl.fontys.lms.business.user.teacher.CreateTeacherUseCase;
import nl.fontys.lms.domain.user.CreateResponse;
import nl.fontys.lms.domain.user.CreateUserRequest;
import nl.fontys.lms.domain.user.teacher.CreateTeacherRequest;
import nl.fontys.lms.persistence.TeacherRepository;
import nl.fontys.lms.persistence.entity.TeacherEntity;
import nl.fontys.lms.security.PasswordUtils;
import nl.fontys.lms.security.SaltUtils;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@AllArgsConstructor
public class CreateTeacherUseCaseImpl implements CreateTeacherUseCase {
    private final TeacherRepository teacherRepository;

    @Override
    public CreateResponse createTeacher(CreateUserRequest request) {
        // Check if the request has the role 'teacher'
        if (UserRole.TEACHER == request.getRole()) {
            return createTeacherWithDepartment(request);
        } else {
            // Create a teacher without a department
            return createTeacherWithoutDepartment(request);
        }
    }

    private CreateResponse createTeacherWithDepartment(CreateUserRequest request) {
        // Generate a random salt
        String salt = SaltUtils.generateSalt();

        // Hash the plain password with the generated salt
        String hashedPassword = PasswordUtils.hashPassword(request.getPassword(), salt);

        // Create a new TeacherEntity with a department
        CreateTeacherRequest teacherRequest = (CreateTeacherRequest) request;
        TeacherEntity teacherEntity = saveNewTeacher(teacherRequest, hashedPassword, salt);

        return CreateResponse.builder()
                .id(teacherEntity.getUserId())
                .build();
    }

    private CreateResponse createTeacherWithoutDepartment(CreateUserRequest request) {
        // Generate a random salt
        String salt = SaltUtils.generateSalt();

        // Hash the plain password with the generated salt
        String hashedPassword = PasswordUtils.hashPassword(request.getPassword(), salt);

        // Create a new TeacherEntity without a department
        TeacherEntity teacherEntity = saveNewTeacher((CreateTeacherRequest) request, null, salt);


        return CreateResponse.builder()
                .id(teacherEntity.getUserId())
                .build();
    }

    private TeacherEntity saveNewTeacher(CreateTeacherRequest request, String hashedPassword, String salt) {
        String role = UserRoleUtil.userRoleToString(UserRole.TEACHER);
        return teacherRepository.save(TeacherEntity.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
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
