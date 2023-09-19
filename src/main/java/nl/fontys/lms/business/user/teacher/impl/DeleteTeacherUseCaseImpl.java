package nl.fontys.lms.business.user.teacher.impl;

import lombok.AllArgsConstructor;
import nl.fontys.lms.business.exception.UserNotFoundException;
import nl.fontys.lms.business.user.teacher.DeleteTeacherUseCase;
import nl.fontys.lms.persistence.TeacherRepository;
import nl.fontys.lms.persistence.entity.TeacherEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteTeacherUseCaseImpl implements DeleteTeacherUseCase {
    private final TeacherRepository teacherRepository;

    @Override
    public void deleteTeacher(Long teacherId) {
        TeacherEntity existingTeacher = teacherRepository.findById(teacherId);
        if (existingTeacher == null) {
            throw new UserNotFoundException();
        }

        teacherRepository.deleteById(teacherId);
    }
}
