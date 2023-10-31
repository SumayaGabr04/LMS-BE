package nl.fontys.lms.business.user.impl;

import nl.fontys.lms.domain.user.UserResponse;
import nl.fontys.lms.domain.user.admin.Admin;
import nl.fontys.lms.domain.user.admin.GetAdminResponse;
import nl.fontys.lms.domain.user.student.GetStudentResponse;
import nl.fontys.lms.domain.user.student.Student;
import nl.fontys.lms.domain.user.teacher.GetTeacherResponse;
import nl.fontys.lms.domain.user.teacher.Teacher;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MapToUserResponse {
    public UserResponse mapToUserResponse(Object user) {
        if (user instanceof Student) {
            Student student = (Student) user;
            return new GetStudentResponse(Optional.of(student));
        } else if (user instanceof Teacher) {
            Teacher teacher = (Teacher) user;
            return new GetTeacherResponse(Optional.of(teacher));
        } else if (user instanceof Admin) {
            Admin admin = (Admin) user;
            return new GetAdminResponse(Optional.of(admin));
        } else {
            throw new IllegalArgumentException("Unsupported user type");
        }
    }

}
