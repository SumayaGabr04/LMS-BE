package nl.fontys.lms.business.user.teacher.impl;

import lombok.AllArgsConstructor;
import nl.fontys.lms.business.exception.UserNotFoundException;
import nl.fontys.lms.business.user.teacher.UpdateTeacherUseCase;
import nl.fontys.lms.domain.user.UpdateUserRequest;
import nl.fontys.lms.domain.user.teacher.UpdateTeacherRequest;
import nl.fontys.lms.persistence.TeacherRepository;
import nl.fontys.lms.persistence.entity.TeacherEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UpdateTeacherUseCaseImpl implements UpdateTeacherUseCase {
    private final TeacherRepository teacherRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void updateTeacher(UpdateUserRequest request) {
        Optional<TeacherEntity> existingTeacherOptional = teacherRepository.findById(request.getId());

        if (existingTeacherOptional.isPresent()) {
            TeacherEntity existingTeacher = existingTeacherOptional.get();

            // Map UpdateUserRequest to UpdateTeacherRequest
            UpdateTeacherRequest teacherRequest = new UpdateTeacherRequest();
            teacherRequest.setUser(request);

            // Update teacher fields as needed
            existingTeacher.setFirstName(teacherRequest.getUser().getFirstName());
            existingTeacher.setLastName(teacherRequest.getUser().getLastName());
            existingTeacher.setEmail(teacherRequest.getUser().getEmail());

            // Hash and salt the new password
            String hashedPassword = passwordEncoder.encode(request.getPassword());

            existingTeacher.setPasswordHash(hashedPassword);
            existingTeacher.setPasswordSalt(null);  // Assuming no salt is used for encoding in PasswordEncoderConfig

            existingTeacher.setDepartment(teacherRequest.getDepartment());
            existingTeacher.setHireDate(teacherRequest.getHireDate());

            teacherRepository.save(existingTeacher);
        } else {
            throw new UserNotFoundException();
        }
    }
}
