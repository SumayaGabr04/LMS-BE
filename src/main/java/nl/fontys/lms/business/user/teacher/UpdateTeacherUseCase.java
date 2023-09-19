package nl.fontys.lms.business.user.teacher;

import nl.fontys.lms.domain.user.teacher.UpdateTeacherRequest;

public interface UpdateTeacherUseCase {
    void updateTeacher(UpdateTeacherRequest request);
}
