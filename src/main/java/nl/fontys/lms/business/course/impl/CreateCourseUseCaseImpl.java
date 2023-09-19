package nl.fontys.lms.business.course.impl;

import lombok.AllArgsConstructor;
import nl.fontys.lms.business.course.CreateCourseUseCase;
import nl.fontys.lms.business.exception.CourseNameAlreadyExists;
import nl.fontys.lms.domain.course.CreateCourseRequest;
import nl.fontys.lms.domain.course.CreateCourseResponse;
import nl.fontys.lms.persistence.CourseRepository;
import nl.fontys.lms.persistence.entity.CourseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateCourseUseCaseImpl implements CreateCourseUseCase {
    private final CourseRepository courseRepository;

    @Override
    public CreateCourseResponse createCourse(CreateCourseRequest request){
        if(courseRepository.existsByCourseName(request.getCourseName())){
            throw new CourseNameAlreadyExists();
        }
        CourseEntity courseEntity = SaveNewCourse(request);

        return CreateCourseResponse.builder()
                .courseId(courseEntity.getId()).build();
    }
    private CourseEntity SaveNewCourse(CreateCourseRequest request){
        CourseEntity newCountry = CourseEntity.builder()
                .courseName(request.getCourseName())
                .description(request.getDescription())
                .instructor(request.getInstructor())
                .enrollmentCapacity(request.getEnrollmentCapacity())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .build();

        return courseRepository.save(newCountry);
    }

}
