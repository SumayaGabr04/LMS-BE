package nl.fontys.lms.business.user.teacher.impl;

import lombok.AllArgsConstructor;
import nl.fontys.lms.business.user.UserRole;
import nl.fontys.lms.business.user.UserRoleUtil;
import nl.fontys.lms.business.user.teacher.CreateTeacherUseCase;
import nl.fontys.lms.domain.user.CreateResponse;
import nl.fontys.lms.domain.user.CreateUserRequest;
import nl.fontys.lms.domain.user.teacher.CreateTeacherRequest;
import nl.fontys.lms.persistence.TeacherRepository;
import nl.fontys.lms.persistence.entity.TeacherEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@AllArgsConstructor
public class CreateTeacherUseCaseImpl implements CreateTeacherUseCase {
    private final TeacherRepository teacherRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public CreateResponse createTeacher(CreateUserRequest request) {
        // Check if the request has the role 'teacher'
        if (UserRole.TEACHER == request.getRole()) {
            return createTeacherWithDepartment(request);
        } else {
            // Create a teacher without a department
            return createTeacherWithoutDepartment(request);
        }
    }


    private CreateResponse createTeacherWithDepartment(CreateUserRequest request) {
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        CreateTeacherRequest teacherRequest = (CreateTeacherRequest) request;
        TeacherEntity teacherEntity = saveNewTeacher(teacherRequest, encodedPassword);

        return CreateResponse.builder()
                .id(teacherEntity.getUserId())
                .build();
    }

    private CreateResponse createTeacherWithoutDepartment(CreateUserRequest request) {
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        TeacherEntity teacherEntity = saveNewTeacher((CreateTeacherRequest) request, encodedPassword);

        return CreateResponse.builder()
                .id(teacherEntity.getUserId())
                .build();
    }

    private TeacherEntity saveNewTeacher(CreateTeacherRequest request, String hashedPassword) {
        String role = UserRoleUtil.userRoleToString(UserRole.TEACHER);
        return teacherRepository.save(TeacherEntity.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .passwordHash(hashedPassword)
                .department(request.getDepartment())
                .hireDate(parseDate(request.getHireDate()))
                .role(role)
                .build());
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
