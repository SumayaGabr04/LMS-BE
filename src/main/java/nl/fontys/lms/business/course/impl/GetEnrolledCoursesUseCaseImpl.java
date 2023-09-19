package nl.fontys.lms.business.course.impl;

import lombok.AllArgsConstructor;
import nl.fontys.lms.business.course.GetEnrolledCoursesUseCase;
import nl.fontys.lms.domain.course.Course;
import nl.fontys.lms.domain.course.GetAllEnrolledCoursesRequest;
import nl.fontys.lms.domain.course.GetAllEnrolledCoursesResponse;
import nl.fontys.lms.persistence.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GetEnrolledCoursesUseCaseImpl implements GetEnrolledCoursesUseCase {
    private final CourseRepository courseRepository;

    @Override
    public GetAllEnrolledCoursesResponse getCourses(GetAllEnrolledCoursesRequest request) {
        ArrayList<Course> courses;
        courses = courseRepository.findAll()
                .stream()
                .map(CourseConverter::convert)
                .collect(Collectors.toCollection(ArrayList::new));

        return GetAllEnrolledCoursesResponse.builder()
                .courses(courses)
                .build();
    }
}
