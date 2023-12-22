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
    public void updateCourse(UpdateCourseRequest request) {
        Optional<CourseEntity> courseOptional = courseRepository.findById(request.getId());

        if (courseOptional.isPresent()) {
            // Update the course properties with values from the request
            CourseEntity updatedCourse = courseOptional.get();
            updatedCourse.setCourseName(request.getCourseName());
            updatedCourse.setDescription(request.getDescription());
            updatedCourse.setInstructor(request.getInstructor());
            updatedCourse.setEnrollmentCapacity(request.getEnrollmentCapacity());
            updatedCourse.setStartDate(request.getStartDate());
            updatedCourse.setEndDate(request.getEndDate());

            // Save the updated course entity
            courseRepository.save(updatedCourse);
        } else {
            throw new InvalidCourseException("COURSE_INVALID");
        }
    }
}
