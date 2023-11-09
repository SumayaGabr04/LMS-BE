package nl.fontys.lms.persistence;

import nl.fontys.lms.persistence.entity.CourseMaterialEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface CourseMaterialRepository extends JpaRepository<CourseMaterialEntity, Long> {
    // Custom query to find course materials by course ID
    List<CourseMaterialEntity> findByCourseId(Long courseId);

    // Custom query to find course materials by course name
    List<CourseMaterialEntity> findByCourseCourseName(String courseName);

    // Custom query to find course materials by course instructor
    List<CourseMaterialEntity> findByCourseInstructor(String instructor);

    // Custom query to find course materials by course start date
    List<CourseMaterialEntity> findByCourseStartDateAfter(Date startDate);

    // Custom query to find course materials by course end date
    List<CourseMaterialEntity> findByCourseEndDateBefore(Date endDate);
}
