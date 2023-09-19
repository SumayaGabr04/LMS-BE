package nl.fontys.lms.business.user.student.impl;

import lombok.AllArgsConstructor;
import nl.fontys.lms.business.exception.EmailAlreadyExists;
import nl.fontys.lms.business.user.student.CreateStudentUseCase;
import nl.fontys.lms.domain.user.CreateResponse;
import nl.fontys.lms.domain.user.student.CreateStudentRequest;
import nl.fontys.lms.persistence.StudentRepository;
import nl.fontys.lms.persistence.entity.StudentEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateStudentUseCaseImpl implements CreateStudentUseCase {
    private final StudentRepository studentRepository;

    @Override
    public CreateResponse createStudent(CreateStudentRequest request) {
        if (studentRepository.existsByEmail(request.getUser().getEmail())) {
            throw new EmailAlreadyExists();
        }
        StudentEntity studentEntity = saveNewStudent(request);

        return CreateResponse.builder()
                .id(studentEntity.getStudentId())
                .build();
    }

    private StudentEntity saveNewStudent(CreateStudentRequest request) {
        StudentEntity newStudent = StudentEntity.builder()
                .firstName(request.getUser().getFirstName())
                .lastName(request.getUser().getLastName())
                .email(request.getUser().getEmail())
                .password(request.getUser().getPassword())
                .build();

        return studentRepository.save(newStudent);
    }

}
