package nl.fontys.lms.business.user.teacher;

import nl.fontys.lms.domain.user.CreateResponse;
import nl.fontys.lms.domain.user.teacher.CreateTeacherRequest;

public interface CreateTeacherUseCase {
    CreateResponse createTeacher(CreateTeacherRequest request);
}
