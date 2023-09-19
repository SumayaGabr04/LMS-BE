package nl.fontys.lms.business.user.student;

import nl.fontys.lms.domain.user.student.GetAllStudentsResponse;
import nl.fontys.lms.domain.user.teacher.GetAllTeachersResponse;

public interface GetAllStudentsUseCase {
    GetAllStudentsResponse getStudents();
}
