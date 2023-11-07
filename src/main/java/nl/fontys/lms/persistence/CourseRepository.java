package nl.fontys.lms.persistence;

import nl.fontys.lms.persistence.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<CourseEntity, Long> {
    boolean existsByCourseName(String courseName);

    Optional<CourseEntity> findById(long courseId);

    void deleteById(long courseId);

    ArrayList<CourseEntity> findAll();

    long count();

    @Query("SELECT c FROM CourseEntity c WHERE c.courseName = :courseName")
    CourseEntity findCourseByCourseName(@Param("courseName") String courseName);
}
