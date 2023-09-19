package nl.fontys.lms.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class EmailAlreadyExists extends ResponseStatusException {
    public EmailAlreadyExists()  {
        super(HttpStatus.BAD_REQUEST, "email already exists");
    }
}
