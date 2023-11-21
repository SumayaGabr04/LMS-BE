package nl.fontys.lms.business.user.student.impl;

import lombok.AllArgsConstructor;

import nl.fontys.lms.business.user.UserRole;
import nl.fontys.lms.business.user.UserRoleUtil;
import nl.fontys.lms.business.user.student.CreateStudentUseCase;
import nl.fontys.lms.domain.user.CreateResponse;

import nl.fontys.lms.domain.user.student.CreateStudentRequest;
import nl.fontys.lms.persistence.StudentRepository;
import nl.fontys.lms.persistence.entity.StudentEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateStudentUseCaseImpl implements CreateStudentUseCase {
    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public CreateResponse createStudent(CreateStudentRequest request) {

        String hashedPassword = passwordEncoder.encode(request.getPassword());

        // Use the conversion method to set 'role' as a string
        String role = UserRoleUtil.userRoleToString(UserRole.STUDENT);

        // Create a new StudentEntity
        StudentEntity.StudentEntityBuilder studentBuilder = StudentEntity.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .passwordHash(hashedPassword)
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
