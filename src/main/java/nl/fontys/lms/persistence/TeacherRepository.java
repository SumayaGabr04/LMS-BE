package nl.fontys.lms.persistence;

import nl.fontys.lms.persistence.entity.CourseEntity;
import nl.fontys.lms.persistence.entity.TeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

public interface TeacherRepository extends JpaRepository<TeacherEntity, Long> {
    boolean existsByEmail(String email);

    ArrayList<TeacherEntity> findAll();

    long count();

//    @Query("SELECT c FROM CourseEntity c JOIN c.teachers t WHERE t.user.userId = :teachersrId")
//    ArrayList<CourseEntity> findCoursesTaughtByTeacherId(@Param("teacherId") long teacherId);
}
