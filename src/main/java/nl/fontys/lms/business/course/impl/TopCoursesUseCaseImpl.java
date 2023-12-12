package nl.fontys.lms.business.course.impl;

import nl.fontys.lms.business.course.TopCoursesUseCase;
import nl.fontys.lms.persistence.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopCoursesUseCaseImpl implements TopCoursesUseCase {
    private final CourseRepository courseRepository;

    @Autowired
    public TopCoursesUseCaseImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Object[]> getTop3CoursesWithMostEnrolledStudents() {
        return courseRepository.getTop3CoursesWithMostEnrolledStudents();
    }
}
