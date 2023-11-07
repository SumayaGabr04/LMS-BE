package nl.fontys.lms.business.user.teacher.impl;

import lombok.AllArgsConstructor;
import nl.fontys.lms.business.course.impl.CourseConverter;
import nl.fontys.lms.business.user.teacher.TeacherCourseUseCase;

import nl.fontys.lms.domain.course.Course;
import nl.fontys.lms.domain.user.teacher.TeacherCoursesRequest;
import nl.fontys.lms.domain.user.teacher.TeacherCoursesResponse;
import nl.fontys.lms.persistence.TeacherRepository;
import nl.fontys.lms.persistence.entity.TeacherEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TeacherCourseUseCaseImpl implements TeacherCourseUseCase {
    private final TeacherRepository teacherRepository;

    @Override
    public TeacherCoursesResponse getTeacherCourses(long teacherId) {
        Optional<TeacherEntity> teacherOptional = teacherRepository.findById(teacherId);

        if (teacherOptional.isPresent()) {
            TeacherEntity teacher = teacherOptional.get();
//            ArrayList<Course> coursesTaught = teacher.getCoursesTaught().stream()
//                    .map(CourseConverter::convert)
//                    .collect(Collectors.toCollection(ArrayList::new));

            // Create a response with the courses taught
            return TeacherCoursesResponse.builder()
//                    .coursesTaught(coursesTaught)
                    .build();
        } else {
            // Return a response with a message if the teacher is not found
            return TeacherCoursesResponse.builder()
                    .message("Teacher not found with ID: " + teacherId)
                    .build();
        }
    }
}
