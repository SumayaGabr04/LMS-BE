package nl.fontys.lms.business.course.impl;

import lombok.AllArgsConstructor;
import nl.fontys.lms.business.course.GetCourseUseCase;
import nl.fontys.lms.domain.course.Course;
import nl.fontys.lms.persistence.CourseRepository;
import nl.fontys.lms.persistence.entity.CourseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GetCourseUseCaseImpl implements GetCourseUseCase {
    private final CourseRepository courseRepository;

//        @Override
//        public Optional<Course> getCourse(long courseId){
//            return courseRepository.findById(courseId).map(CourseConverter::convert);
//        }
    @Override
    public Optional<Course> getCourse(long courseId) {
        CourseEntity courseEntity = courseRepository.findById(courseId);
        if (courseEntity != null) {
            return Optional.of(CourseConverter.convert(courseEntity));
        } else {
            return Optional.empty();
        }
    }

}
