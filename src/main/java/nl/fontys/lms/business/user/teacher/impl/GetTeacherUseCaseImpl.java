package nl.fontys.lms.business.user.teacher.impl;

import lombok.AllArgsConstructor;
import nl.fontys.lms.business.course.GetEnrolledCoursesUseCase;
import nl.fontys.lms.business.exception.UserNotFoundException;
import nl.fontys.lms.business.user.teacher.GetTeacherUseCase;
import nl.fontys.lms.domain.course.GetAllEnrolledCoursesRequest;
import nl.fontys.lms.domain.course.GetAllEnrolledCoursesResponse;
import nl.fontys.lms.domain.user.User;
import nl.fontys.lms.domain.user.teacher.GetTeacherResponse;
import nl.fontys.lms.domain.user.teacher.Teacher;
import nl.fontys.lms.persistence.TeacherRepository;
import nl.fontys.lms.persistence.entity.TeacherEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GetTeacherUseCaseImpl implements GetTeacherUseCase {
    private final TeacherRepository teacherRepository;
    private final GetEnrolledCoursesUseCase getEnrolledCoursesUseCase;

    @Override
    public Optional<Teacher> getTeacher(Long teacherId) {
        TeacherEntity teacherEntity = teacherRepository.findById(teacherId);
        if (teacherEntity == null) {
            throw new UserNotFoundException();
        }

        // Use the GetEnrolledCoursesUseCase to fetch the list of courses taught by the teacher
        GetAllEnrolledCoursesRequest request = GetAllEnrolledCoursesRequest.builder()
                .userId(teacherId)
                .build();
        GetAllEnrolledCoursesResponse enrolledCoursesResponse = getEnrolledCoursesUseCase.getCourses(request);

        // Use .map(TeacherConverter::convert) to convert the TeacherEntity to a Teacher object
        Optional<Teacher> teacherOptional = Optional.of(teacherEntity).map(TeacherConverter::convert);

        // Set the courses taught by the teacher
        teacherOptional.ifPresent(teacher -> teacher.setCoursesTaught(enrolledCoursesResponse.getCourses()));

        return teacherOptional;
    }
}
