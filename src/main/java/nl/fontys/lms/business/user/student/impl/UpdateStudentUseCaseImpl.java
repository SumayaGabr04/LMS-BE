package nl.fontys.lms.business.user.student.impl;

import lombok.AllArgsConstructor;
import nl.fontys.lms.business.exception.UserNotFoundException;
import nl.fontys.lms.business.user.student.UpdateStudentUseCase;
import nl.fontys.lms.domain.user.UpdateUserRequest;
import nl.fontys.lms.domain.user.student.UpdateStudentRequest;
import nl.fontys.lms.persistence.StudentRepository;
import nl.fontys.lms.persistence.entity.StudentEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UpdateStudentUseCaseImpl implements UpdateStudentUseCase {
    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void updateStudent(UpdateUserRequest request) {
        Optional<StudentEntity> existingStudentOptional = studentRepository.findById(request.getId());

        if (existingStudentOptional.isPresent()) {
            StudentEntity existingStudent = existingStudentOptional.get();

            // Map UpdateUserRequest to UpdateStudentRequest
            UpdateStudentRequest studentRequest = new UpdateStudentRequest();
            studentRequest.setUser(request);

            // Update student fields as needed
            existingStudent.setFirstName(studentRequest.getUser().getFirstName());
            existingStudent.setLastName(studentRequest.getUser().getLastName());
            existingStudent.setEmail(studentRequest.getUser().getEmail());

            // Hash and salt the new password
            String hashedPassword = passwordEncoder.encode(request.getPassword());

            existingStudent.setPasswordHash(hashedPassword);
            existingStudent.setPasswordSalt(null);  // Assuming no salt is used for encoding in PasswordEncoderConfig

            existingStudent.setMajor(studentRequest.getMajor());

            studentRepository.save(existingStudent);
        } else {
            throw new UserNotFoundException();
        }
    }
}
