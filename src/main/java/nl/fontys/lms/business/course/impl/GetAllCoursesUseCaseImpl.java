package nl.fontys.lms.business.course.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import nl.fontys.lms.business.course.GetAllCoursesUseCase;
import nl.fontys.lms.domain.course.Course;
import nl.fontys.lms.domain.course.GetAllCoursesResponse;
import nl.fontys.lms.persistence.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GetAllCoursesUseCaseImpl implements GetAllCoursesUseCase {
    private final CourseRepository courseRepository;

    @Transactional
    @Override
    public GetAllCoursesResponse getCourses() {
        ArrayList<Course> courses = courseRepository.findAll()
                .stream()
                .map(CourseConverter::convert)
                .collect(Collectors.toCollection(ArrayList::new));

        return GetAllCoursesResponse.builder().courses(courses).build();
    }

    @Transactional
    public GetAllCoursesResponse searchCourses(String searchTerm) {
        ArrayList<Course> courses = courseRepository.searchCourses(searchTerm)
                .stream()
                .map(CourseConverter::convert)
                .collect(Collectors.toCollection(ArrayList::new));

        return GetAllCoursesResponse.builder().courses(courses).build();
    }
}
