package nl.fontys.lms.business.course.validations;

import java.util.List;

public class ValidationResult {
    private final List<String> errorMessages;

    public ValidationResult(List<String> errorMessages) {
        this.errorMessages = errorMessages;
    }

    public List<String> getErrorMessages() {
        return errorMessages;
    }

    public boolean isValid() {
        return errorMessages.isEmpty();
    }
}
