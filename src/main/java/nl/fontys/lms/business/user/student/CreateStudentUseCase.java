package nl.fontys.lms.business.user.student;

import lombok.AllArgsConstructor;
import nl.fontys.lms.domain.user.CreateResponse;
import nl.fontys.lms.domain.user.CreateUserRequest;
import nl.fontys.lms.domain.user.student.CreateStudentRequest;
import org.springframework.stereotype.Service;

public interface CreateStudentUseCase{
    CreateResponse createStudent(CreateStudentRequest request);
//    CreateResponse createStudent(CreateUserRequest request);
}
