package nl.fontys.lms.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidCourseException extends ResponseStatusException {
    public InvalidCourseException(String errorCode) {
        super(HttpStatus.BAD_REQUEST, errorCode);
    }
}
