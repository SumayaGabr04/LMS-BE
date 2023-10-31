package nl.fontys.lms.business.user.teacher.impl;

import lombok.AllArgsConstructor;
import nl.fontys.lms.business.exception.UserNotFoundException;
import nl.fontys.lms.business.user.UpdateUserUseCase;
import nl.fontys.lms.business.user.teacher.UpdateTeacherUseCase;
import nl.fontys.lms.domain.user.UpdateUserRequest;
import nl.fontys.lms.domain.user.teacher.UpdateTeacherRequest;
import nl.fontys.lms.persistence.TeacherRepository;
import nl.fontys.lms.persistence.entity.TeacherEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateTeacherUseCaseImpl implements UpdateTeacherUseCase {
    private final TeacherRepository teacherRepository;

    @Override
    public void updateTeacher(UpdateUserRequest request) {
        TeacherEntity existingTeacher = teacherRepository.findById(request.getId());
        if (existingTeacher == null) {
            throw new UserNotFoundException(); // Create this exception class
        }

        // Map UpdateUserRequest to UpdateTeacherRequest
        UpdateTeacherRequest teacherRequest = new UpdateTeacherRequest();
        teacherRequest.setUser(request);

        // Update teacher fields as needed
        existingTeacher.setFirstName(teacherRequest.getUser().getFirstName());
        existingTeacher.setLastName(teacherRequest.getUser().getLastName());
        existingTeacher.setEmail(teacherRequest.getUser().getEmail());
        existingTeacher.setPassword(teacherRequest.getUser().getPassword());
        existingTeacher.setDepartment(teacherRequest.getDepartment());
        existingTeacher.setHireDate(teacherRequest.getHireDate());

        teacherRepository.save(existingTeacher);
    }
}
