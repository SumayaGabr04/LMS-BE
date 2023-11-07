package nl.fontys.lms.persistence;

import nl.fontys.lms.persistence.entity.CourseEntity;
import nl.fontys.lms.persistence.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
    boolean existsByEmail(String email);

    ArrayList<StudentEntity> findAll();

    long count();

    @Query("SELECT c FROM CourseEntity c JOIN c.enrolledStudents s WHERE s.user.userId = :studentId")
    ArrayList<CourseEntity> findEnrolledCoursesByStudentId(@Param("studentId") long studentId);
}
