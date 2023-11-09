package nl.fontys.lms.business.user.student.impl;

import lombok.AllArgsConstructor;
import nl.fontys.lms.business.exception.EmailAlreadyExists;
import nl.fontys.lms.business.user.UserRole;
import nl.fontys.lms.business.user.UserRoleUtil;
import nl.fontys.lms.business.user.student.CreateStudentUseCase;
import nl.fontys.lms.domain.user.CreateResponse;
import nl.fontys.lms.domain.user.CreateUserRequest;
import nl.fontys.lms.domain.user.User;
import nl.fontys.lms.domain.user.student.CreateStudentRequest;
import nl.fontys.lms.persistence.StudentRepository;
import nl.fontys.lms.persistence.UserRepository;
import nl.fontys.lms.persistence.entity.StudentEntity;
import nl.fontys.lms.persistence.entity.UserEntity;
import nl.fontys.lms.security.PasswordUtils;
import nl.fontys.lms.security.SaltUtils;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateStudentUseCaseImpl implements CreateStudentUseCase {
    private final StudentRepository studentRepository;

    @Override
    public CreateResponse createStudent(CreateStudentRequest request) {
        // Generate a random salt
        String salt = SaltUtils.generateSalt();

        // Hash the plain password with the generated salt
        String hashedPassword = PasswordUtils.hashPassword(request.getPassword(), salt);

        // Use the conversion method to set 'role' as a string
        String role = UserRoleUtil.userRoleToString(UserRole.STUDENT);

        // Create a new StudentEntity
        StudentEntity.StudentEntityBuilder studentBuilder = StudentEntity.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .passwordHash(hashedPassword)
                .passwordSalt(salt)
                .role(role);

        // Set 'major' if it's available in the request
        if (request.getMajor() != null) {
            studentBuilder.major(request.getMajor());
        }

        StudentEntity newStudent = studentBuilder.build();

        StudentEntity studentEntity = saveNewStudent(newStudent);

        return CreateResponse.builder()
                .id(studentEntity.getUserId())
                .build();
    }

    private StudentEntity saveNewStudent(StudentEntity studentEntity) {
        return studentRepository.save(studentEntity);
    }
}
