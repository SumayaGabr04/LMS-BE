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
    Optional<CourseEntity> optionalCourseEntity = courseRepository.findById(courseId);
    return optionalCourseEntity.map(CourseConverter::convert);
}

}
