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
    public Optional<TeacherCoursesResponse> getTeacherCourses(long teacherId) {
        TeacherEntity teacher = teacherRepository.findById(teacherId);

        if (teacher != null) {
            ArrayList<Course> coursesTaught = teacher.getCoursesTaught().stream()
                    .map(CourseConverter::convert)
                    .collect(Collectors.toCollection(ArrayList::new));

            // Create a response with the courses taught
            return Optional.of(TeacherCoursesResponse.builder()
                    .coursesTaught(coursesTaught)
                    .build());
        } else {
            // Return a response with a message if the teacher is not found
            return Optional.of(TeacherCoursesResponse.builder()
                    .message("Teacher not found with ID: " + teacherId)
                    .build());
        }
    }

}
