package nl.fontys.lms.business.course.impl;


import lombok.AllArgsConstructor;
import nl.fontys.lms.business.course.DeleteCourseUseCase;
import nl.fontys.lms.persistence.CourseRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteCourseUseCaseImpl implements DeleteCourseUseCase {
    private final CourseRepository courseRepository;

    @Override
    public void deleteCourse(long courseId){
        this.courseRepository.deleteById(courseId);
    }
}
