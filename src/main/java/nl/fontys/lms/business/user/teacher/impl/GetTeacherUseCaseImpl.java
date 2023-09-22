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

import java.util.ArrayList;
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

        // Convert TeacherEntity to Teacher using a converter
        Optional<Teacher> teacherOptional = Optional.ofNullable(teacherEntity).map(TeacherConverter::convert);

        return teacherOptional;
    }

}
