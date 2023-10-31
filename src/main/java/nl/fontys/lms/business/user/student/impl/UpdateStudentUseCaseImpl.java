package nl.fontys.lms.business.user.student.impl;

import lombok.AllArgsConstructor;
import nl.fontys.lms.business.exception.UserNotFoundException;
import nl.fontys.lms.business.user.student.UpdateStudentUseCase;
import nl.fontys.lms.domain.user.UpdateUserRequest;
import nl.fontys.lms.domain.user.student.UpdateStudentRequest;
import nl.fontys.lms.persistence.StudentRepository;
import nl.fontys.lms.persistence.entity.StudentEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateStudentUseCaseImpl implements UpdateStudentUseCase {
    private final StudentRepository studentRepository;

    @Override
    public void updateStudent(UpdateUserRequest request) {
        StudentEntity existingStudent = studentRepository.findById(request.getId());
        if (existingStudent == null) {
            throw new UserNotFoundException();
        }

        // Map UpdateUserRequest to UpdateStudentRequest
        UpdateStudentRequest studentRequest = new UpdateStudentRequest();
        studentRequest.setUser(request);

        // Update student fields as needed
        existingStudent.setFirstName(studentRequest.getUser().getFirstName());
        existingStudent.setLastName(studentRequest.getUser().getLastName());
        existingStudent.setEmail(studentRequest.getUser().getEmail());
        existingStudent.setPassword(studentRequest.getUser().getPassword());
        existingStudent.setMajor(studentRequest.getMajor());
        existingStudent.setEnrollmentDate(studentRequest.getEnrollmentDate());

        studentRepository.save(existingStudent);
    }
}
