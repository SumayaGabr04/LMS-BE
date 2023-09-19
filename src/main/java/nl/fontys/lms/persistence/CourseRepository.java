package nl.fontys.lms.persistence;

import nl.fontys.lms.persistence.entity.CourseEntity;

import java.util.ArrayList;

public interface CourseRepository {
    boolean existsByCourseName(String courseName);

    boolean existsById(long courseId);

    void deleteById(long courseId);

    CourseEntity findById(long courseId);

    ArrayList<CourseEntity> findAll();
    CourseEntity save(CourseEntity course);
    int count();
}
