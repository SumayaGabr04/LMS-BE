package nl.fontys.lms.businesss.course;

import nl.fontys.lms.business.exception.*;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CourseNameAlreadyExistsTest {
    @Test
    void testCourseNameAlreadyExistsException() {
        CourseNameAlreadyExists exception = new CourseNameAlreadyExists();

        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        assertEquals("invalid course name", exception.getReason());
    }

    @Test
    void testEmailAlreadyExistsException() {
        EmailAlreadyExists exception = new EmailAlreadyExists();

        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        assertEquals("email already exists", exception.getReason());
    }

    @Test
    void testInvalidCourseException() {
        InvalidCourseException exception = new InvalidCourseException("ERROR_CODE");

        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        assertEquals("ERROR_CODE", exception.getReason());
    }

    @Test
    void testInvalidCredentialsException() {
        InvalidCredentialsException exception = new InvalidCredentialsException();

        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        assertEquals("INVALID_CREDENTIALS", exception.getReason());
    }

    @Test
    void testInvalidRoleException() {
        InvalidRoleException exception = new InvalidRoleException();

        assertEquals("Invalid or unknown user role.", exception.getMessage());
    }

    @Test
    void testUserNotFoundException() {
        UserNotFoundException exception = new UserNotFoundException();

        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        assertEquals("invalid course name", exception.getReason());
    }
}
