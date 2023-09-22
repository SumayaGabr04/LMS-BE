package nl.fontys.lms.business.course.validations;

import java.util.ArrayList;

public class ValidationResult {
    private final ArrayList<String> errorMessages;

    public ValidationResult(ArrayList<String> errorMessages) {
        this.errorMessages = errorMessages;
    }

    public ArrayList<String> getErrorMessages() {
        return errorMessages;
    }

    public boolean isValid() {
        return errorMessages.isEmpty();
    }
}
