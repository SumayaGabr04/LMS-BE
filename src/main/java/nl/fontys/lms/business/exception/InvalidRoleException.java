package nl.fontys.lms.business.exception;

public class InvalidRoleException extends RuntimeException {
    public InvalidRoleException() {
        super("Invalid or unknown user role.");
    }
}
