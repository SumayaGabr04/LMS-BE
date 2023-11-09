package nl.fontys.lms.persistence;

import nl.fontys.lms.persistence.entity.EnrollmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface EnrollmentRepository extends JpaRepository<EnrollmentEntity, Long> {
    @Query("SELECT e FROM EnrollmentEntity e WHERE e.student.userId = :studentId")
    List<EnrollmentEntity> findAllEnrollmentsForStudent(Long studentId);

    @Query("SELECT e FROM EnrollmentEntity e WHERE e.course.id = :courseId")
    List<EnrollmentEntity> findAllEnrollmentsForCourse(Long courseId);

    @Query("SELECT COUNT(e) > 0 FROM EnrollmentEntity e WHERE e.student.userId = :studentId AND e.course.id = :courseId")
    boolean isStudentEnrolledInCourse(Long studentId, Long courseId);

    @Query("SELECT e.enrollmentDate FROM EnrollmentEntity e WHERE e.student.userId = :studentId AND e.course.id = :courseId")
    Date getEnrollmentDateForStudentInCourse(Long studentId, Long courseId);
}