package nl.fontys.lms.business.user.teacher;

import nl.fontys.lms.domain.user.CreateResponse;
import nl.fontys.lms.domain.user.CreateUserRequest;

public interface CreateTeacherUseCase {
    CreateResponse createTeacher(CreateUserRequest request);
}
