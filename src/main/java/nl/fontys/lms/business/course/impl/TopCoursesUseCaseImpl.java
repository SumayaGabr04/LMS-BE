package nl.fontys.lms.business.course.impl;

import nl.fontys.lms.business.course.TopCoursesUseCase;
import nl.fontys.lms.domain.course.TopCourseInfo;
import nl.fontys.lms.domain.course.TopCoursesResponse;
import nl.fontys.lms.persistence.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TopCoursesUseCaseImpl implements TopCoursesUseCase {
    private final CourseRepository courseRepository;

    @Autowired
    public TopCoursesUseCaseImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public TopCoursesResponse getTop3CoursesWithMostEnrolledStudents() {
        List<Object[]> top3Courses = courseRepository.getTop3CoursesWithMostEnrolledStudents();

        // Convert Object[] to TopCourseInfo
        List<TopCourseInfo> topCourseInfoList = top3Courses.stream()
                .map(arr -> new TopCourseInfo((String) arr[0], (Long) arr[1]))
                .collect(Collectors.toList());

        return new TopCoursesResponse(topCourseInfoList);
    }
}
