package nl.fontys.lms.business.course.impl;

import lombok.AllArgsConstructor;
import nl.fontys.lms.business.course.CreateCourseUseCase;
import nl.fontys.lms.business.course.validations.CreateCourseRequestValidator;
import nl.fontys.lms.business.course.validations.ValidationResult;
import nl.fontys.lms.domain.course.CreateCourseRequest;
import nl.fontys.lms.domain.course.CreateCourseResponse;
import nl.fontys.lms.persistence.CourseRepository;
import nl.fontys.lms.persistence.entity.CourseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;

@Service
@AllArgsConstructor
public class CreateCourseUseCaseImpl implements CreateCourseUseCase {
    private final CourseRepository courseRepository;
    private final CreateCourseRequestValidator requestValidator;

@Override
public CreateCourseResponse createCourse(CreateCourseRequest request) {
    // Validate the request before processing
    ValidationResult validationResult = requestValidator.validateCourseRequest(request);
    if (!validationResult.isValid()) {
        return CreateCourseResponse.builder()
                .errorMessages(validationResult.getErrorMessages())
                .build();
    }

    // Create and save the new course
    CourseEntity courseEntity = saveNewCourse(request);

    if (courseEntity != null) {
        return CreateCourseResponse.builder()
                .courseId(courseEntity.getId())
                .build();
    } else {
        // Handle the case where courseEntity is null
        // You can return an appropriate error response or throw an exception
        return CreateCourseResponse.builder()
                .errorMessages(new ArrayList<>(Collections.singletonList("Failed to create the course")))
                .build();

    }
}
    private CourseEntity saveNewCourse(CreateCourseRequest request) {
        CourseEntity newCourse = CourseEntity.builder()
                .courseName(request.getCourseName())
                .description(request.getDescription())
                .instructor(request.getInstructor())
                .enrollmentCapacity(request.getEnrollmentCapacity())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .courseMaterials(new ArrayList<>())
                .enrolledStudents(new ArrayList<>())
                .build();

        return courseRepository.save(newCourse);
    }

}
