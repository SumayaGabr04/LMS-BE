package nl.fontys.lms.persistence.impl;

import nl.fontys.lms.persistence.CourseRepository;
import nl.fontys.lms.persistence.entity.CourseEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public class FakeCourseRepositoryImpl implements CourseRepository {
    private static long NEXT_ID = 1;

    private final ArrayList<CourseEntity> savedCourses;

    public FakeCourseRepositoryImpl() {
        this.savedCourses = new ArrayList<>();
    }

    @Override
    public boolean existsByCourseName(String courseName) {
        return this.savedCourses
                .stream()
                .anyMatch(courseEntity -> courseEntity.getCourseName().equals(courseName));
    }

    @Override
    public void deleteById(long courseId) {
        this.savedCourses.removeIf(courseEntity -> courseEntity.getId() == courseId);
    }

    @Override
    public boolean existsById(long courseId) {
        return this.savedCourses
                .stream()
                .anyMatch(courseEntity -> courseEntity.getId() == courseId);
    }

    @Override
    public Optional<CourseEntity> findById(long courseId) {
        return this.savedCourses
                .stream()
                .filter(courseEntity -> courseEntity.getId() == courseId)
                .findFirst();
    }

    @Override
    public CourseEntity save(CourseEntity course) {
        course.setId(NEXT_ID);
        NEXT_ID++;
        this.savedCourses.add(course);
        return course;
    }

    @Override
    public ArrayList<CourseEntity> findAll() {
        return new ArrayList<>(savedCourses);
    }

    @Override
    public int count() {
        return this.savedCourses.size();
    }

}
