package nl.fontys.lms.business.user.teacher.impl;

import lombok.AllArgsConstructor;
import nl.fontys.lms.business.exception.UserNotFoundException;
import nl.fontys.lms.business.user.UpdateUserUseCase;
import nl.fontys.lms.business.user.teacher.UpdateTeacherUseCase;
import nl.fontys.lms.domain.user.teacher.UpdateTeacherRequest;
import nl.fontys.lms.persistence.TeacherRepository;
import nl.fontys.lms.persistence.entity.TeacherEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateTeacherUseCaseImpl implements UpdateTeacherUseCase {
    private final TeacherRepository teacherRepository;

    @Override
    public void updateTeacher(UpdateTeacherRequest request) {
        TeacherEntity existingTeacher = teacherRepository.findById(request.getId());
        if (existingTeacher == null) {
            throw new UserNotFoundException(); // Create this exception class
        }

        // Update teacher fields as needed
        existingTeacher.setFirstName(request.getUser().getFirstName());
        existingTeacher.setLastName(request.getUser().getLastName());
        existingTeacher.setEmail(request.getUser().getEmail());
        existingTeacher.setPassword(request.getUser().getPassword());
        existingTeacher.setDepartment(request.getDepartment());
        existingTeacher.setHireDate(request.getHireDate());

        teacherRepository.save(existingTeacher);

    }
}
