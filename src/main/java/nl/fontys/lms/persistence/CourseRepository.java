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
    Optional<CourseEntity> findCourseByCourseName(@Param("courseName") String courseName);

    @Query("SELECT c FROM CourseEntity c WHERE " +
            "LOWER(c.courseName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(c.instructor) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(c.description) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    ArrayList<CourseEntity> searchCourses(@Param("searchTerm") String searchTerm);

    @Query("SELECT c.id, c.courseName, COUNT(e.student.userId) AS totalStudents " +
            "FROM CourseEntity c " +
            "LEFT JOIN EnrollmentEntity e ON c.id = e.course.id " +
            "GROUP BY c.id, c.courseName")
    ArrayList<Object[]> getTotalStudentsPerCourse();

}
