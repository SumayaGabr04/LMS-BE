package nl.fontys.lms.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class CourseNameAlreadyExists extends ResponseStatusException {

    public CourseNameAlreadyExists() {
        super(HttpStatus.BAD_REQUEST, "invalid course name");
    }
}
