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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
                .id(teacherEntity.getUserId())
                .build();
    }

    private TeacherEntity saveNewTeacher(CreateTeacherRequest request) {
        TeacherEntity newTeacher = TeacherEntity.builder()
                .firstName(request.getUser().getFirstName())
                .lastName(request.getUser().getLastName())
                .email(request.getUser().getEmail())
                .passwordHash(request.getUser().getPassword())
                .department(request.getDepartment())
                .hireDate(parseDate(request.getHireDate()))
                .build();

        return teacherRepository.save(newTeacher);
    }

    private Date parseDate(String dateStr) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Update the format as needed
            return dateFormat.parse(dateStr);
        } catch (ParseException e) {
            // Handle the parsing exception
            e.printStackTrace();
            return null; // or throw an exception
        }
    }
}
