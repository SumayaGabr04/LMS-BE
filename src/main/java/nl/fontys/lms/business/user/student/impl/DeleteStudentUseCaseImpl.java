package nl.fontys.lms.business.user.student.impl;

import lombok.AllArgsConstructor;
import nl.fontys.lms.business.exception.UserNotFoundException;
import nl.fontys.lms.business.user.student.DeleteStudentUseCase;
import nl.fontys.lms.persistence.StudentRepository;
import nl.fontys.lms.persistence.entity.StudentEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteStudentUseCaseImpl implements DeleteStudentUseCase {
    private final StudentRepository studentRepository;

    @Override
    public void deleteStudent(Long studentId) {
        StudentEntity existingStudent = studentRepository.findById(studentId)
                .orElseThrow(UserNotFoundException::new);

        studentRepository.deleteById(studentId);
    }
}
