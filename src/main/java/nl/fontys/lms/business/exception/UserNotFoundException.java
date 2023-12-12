package nl.fontys.lms.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserNotFoundException extends ResponseStatusException {
    public UserNotFoundException() {
        super(HttpStatus.BAD_REQUEST, "invalid course name");
    }
}
