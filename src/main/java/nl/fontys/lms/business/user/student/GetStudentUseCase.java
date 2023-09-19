package nl.fontys.lms.business.user.student;

import nl.fontys.lms.domain.user.student.GetStudentResponse;
import nl.fontys.lms.domain.user.student.Student;

import java.util.Optional;

public interface GetStudentUseCase {
    Optional<Student> getStudent(Long studentId);
}
