package nl.fontys.lms.business.user.teacher;

import nl.fontys.lms.domain.user.teacher.GetTeacherResponse;
import nl.fontys.lms.domain.user.teacher.Teacher;

import java.util.Optional;

public interface GetTeacherUseCase {
    Optional<Teacher> getTeacher(Long teacherId);
}
