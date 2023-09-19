package nl.fontys.lms.business.user.student.impl;

import lombok.AllArgsConstructor;
import nl.fontys.lms.business.exception.UserNotFoundException;
import nl.fontys.lms.business.user.student.UpdateStudentUseCase;
import nl.fontys.lms.domain.user.student.UpdateStudentRequest;
import nl.fontys.lms.persistence.StudentRepository;
import nl.fontys.lms.persistence.entity.StudentEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateStudentUseCaseImpl implements UpdateStudentUseCase {
    private final StudentRepository studentRepository;

    @Override
    public void updateStudent(UpdateStudentRequest request) {
        StudentEntity existingStudent = studentRepository.findById(request.getId());
        if (existingStudent == null) {
            throw new UserNotFoundException();
        }

        // Update student fields as needed
        existingStudent.setFirstName(request.getUser().getFirstName());
        existingStudent.setLastName(request.getUser().getLastName());
        existingStudent.setEmail(request.getUser().getEmail());
        existingStudent.setPassword(request.getUser().getPassword());
        existingStudent.setMajor(request.getMajor());
        existingStudent.setEnrollmentDate(request.getEnrollmentDate());

        studentRepository.save(existingStudent);
    }
}
