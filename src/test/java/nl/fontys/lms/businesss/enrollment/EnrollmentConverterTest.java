package nl.fontys.lms.businesss.enrollment;

import nl.fontys.lms.business.enrollment.impl.EnrollmentConverter;
import nl.fontys.lms.domain.Enrollment.Enrollment;
import nl.fontys.lms.persistence.entity.CourseEntity;
import nl.fontys.lms.persistence.entity.EnrollmentEntity;
import nl.fontys.lms.persistence.entity.UserEntity;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EnrollmentConverterTest {
    @Test
    void testConvert() {
        // Arrange
        EnrollmentEntity enrollmentEntity = new EnrollmentEntity();
        enrollmentEntity.setId(1L);

        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setId(101L);

        UserEntity studentEntity = new UserEntity();
        studentEntity.setUserId(201L);

        enrollmentEntity.setCourse(courseEntity);
        enrollmentEntity.setStudent(studentEntity);
        enrollmentEntity.setEnrollmentDate(new Date());

        // Act
        Enrollment result = EnrollmentConverter.convert(enrollmentEntity);

        // Assert
        assertEquals(courseEntity.getId(), result.getCourse().getId());
        assertEquals(studentEntity.getUserId(), result.getStudentId());
        assertEquals(enrollmentEntity.getEnrollmentDate(), result.getEnrollmentDate());
    }
}
