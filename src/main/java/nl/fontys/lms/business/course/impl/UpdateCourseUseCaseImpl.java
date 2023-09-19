package nl.fontys.lms.business.course.impl;

import lombok.AllArgsConstructor;
import nl.fontys.lms.business.course.UpdateCourseUseCase;
import nl.fontys.lms.business.exception.InvalidCourseException;
import nl.fontys.lms.domain.course.UpdateCourseRequest;
import nl.fontys.lms.persistence.CourseRepository;
import nl.fontys.lms.persistence.entity.CourseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UpdateCourseUseCaseImpl implements UpdateCourseUseCase {
    private final CourseRepository courseRepository;

    @Override
    public void UpdateCourse(UpdateCourseRequest request){
        Optional<CourseEntity> courseOptional = Optional.ofNullable(courseRepository.findById(request.getId()));
        if(courseOptional.isEmpty()){
            throw new InvalidCourseException("COURSE_INVALID");
        }
        CourseEntity updatedCourse = courseOptional.get();
    }
}
