package nl.fontys.lms.persistance;

import jakarta.transaction.Transactional;
import nl.fontys.lms.persistence.CourseRepository;
import nl.fontys.lms.persistence.entity.CourseEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CourseRepositoryTest {
    @Autowired
    private CourseRepository courseRepository;

    @Test
    void testSaveAndFindCourseByCourseName() {
        // Create a new course
        CourseEntity course = new CourseEntity();
        course.setCourseName("Test Course");
        course.setDescription("This is a test course");

        // Save the course to the repository
        courseRepository.save(course);

        // Find the course by course name
        CourseEntity foundCourse = courseRepository.findCourseByCourseName("Test Course")
                .orElseThrow(() -> new RuntimeException("Course not found"));

        assertEquals("Test Course", foundCourse.getCourseName());

    }

    @Test
    void testExistsByCourseName() {
        // Create a new course
        CourseEntity course = new CourseEntity();
        course.setCourseName("Test Course");
        course.setDescription("This is a test course");

        // Save the course to the repository
        courseRepository.save(course);

        // Check if the course with the given name exists
        boolean exists = courseRepository.existsByCourseName("Test Course");

        // Verify that the course exists
        assertTrue(exists);
    }

    @Test
    void testFindAll() {
        // Create and save a few courses
        CourseEntity course1 = new CourseEntity();
        course1.setCourseName("Course 1");
        course1.setDescription("This is course 1");
        courseRepository.save(course1);

        CourseEntity course2 = new CourseEntity();
        course2.setCourseName("Course 2");
        course2.setDescription("This is course 2");
        courseRepository.save(course2);

        // Retrieve all courses from the repository
        Iterable<CourseEntity> courses = courseRepository.findAll();

        // Verify that there are at least 2 courses
        long courseCount = courseRepository.count();
        assertTrue(courseCount >= 2);
    }
}
