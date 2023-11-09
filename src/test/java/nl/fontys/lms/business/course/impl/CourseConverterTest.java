package nl.fontys.lms.business.course.impl;

import nl.fontys.lms.domain.course.Course;
import nl.fontys.lms.persistence.entity.CourseEntity;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CourseConverterTest {
    @Test
    void testConvert() {
        // Arrange
        CourseEntity courseEntity = CourseEntity.builder()
                .id(1L)
                .courseName("Math101")
                .description("Math Course")
                .instructor("John Doe")
                .enrollmentCapacity(30)
                .startDate(new Date())
                .endDate(new Date())
                .build();

        // Act
        Course course = CourseConverter.convert(courseEntity);

        // Assert
        assertEquals(courseEntity.getId(), course.getId());
        assertEquals(courseEntity.getCourseName(), course.getCourseName());
        assertEquals(courseEntity.getDescription(), course.getDescription());
        assertEquals(courseEntity.getInstructor(), course.getInstructor());
        assertEquals(courseEntity.getEnrollmentCapacity(), course.getEnrollmentCapacity());
        assertEquals(courseEntity.getStartDate(), course.getStartDate());
        assertEquals(courseEntity.getEndDate(), course.getEndDate());
    }
}
