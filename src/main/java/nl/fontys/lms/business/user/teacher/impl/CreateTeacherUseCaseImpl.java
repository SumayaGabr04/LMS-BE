package nl.fontys.lms.business.user.teacher.impl;

import lombok.AllArgsConstructor;
import nl.fontys.lms.business.exception.EmailAlreadyExists;
import nl.fontys.lms.business.user.teacher.CreateTeacherUseCase;
import nl.fontys.lms.domain.user.CreateResponse;
import nl.fontys.lms.domain.user.CreateUserRequest;
import nl.fontys.lms.domain.user.teacher.CreateTeacherRequest;
import nl.fontys.lms.persistence.TeacherRepository;
import nl.fontys.lms.persistence.entity.TeacherEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateTeacherUseCaseImpl implements CreateTeacherUseCase {
    private final TeacherRepository teacherRepository;

    @Override
    public CreateResponse createTeacher(CreateUserRequest request) {
        // Map CreateUserRequest to CreateTeacherRequest
        CreateTeacherRequest teacherRequest = new CreateTeacherRequest();
        teacherRequest.setUser(request);

        if (teacherRepository.existsByEmail(teacherRequest.getUser().getEmail())) {
            throw new EmailAlreadyExists();
        }
        TeacherEntity teacherEntity = saveNewTeacher(teacherRequest);

        return CreateResponse.builder()
                .id(teacherEntity.getTeacherId())
                .build();
    }

    private TeacherEntity saveNewTeacher(CreateTeacherRequest request) {
        TeacherEntity newTeacher = TeacherEntity.builder()
                .firstName(request.getUser().getFirstName())
                .lastName(request.getUser().getLastName())
                .email(request.getUser().getEmail())
                .password(request.getUser().getPassword())
                .department(request.getDepartment())
                .hireDate(request.getHireDate())
                .build();

        return teacherRepository.save(newTeacher);
    }
}
