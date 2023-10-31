package nl.fontys.lms.business.user.student;

import nl.fontys.lms.domain.user.UpdateUserRequest;
import nl.fontys.lms.domain.user.student.UpdateStudentRequest;

public interface UpdateStudentUseCase {
    void updateStudent(UpdateUserRequest request);
}
