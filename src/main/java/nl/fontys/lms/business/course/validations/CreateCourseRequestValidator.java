package nl.fontys.lms.business.course.validations;

import nl.fontys.lms.domain.course.CreateCourseRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

@Component
public class CreateCourseRequestValidator {
    public ValidationResult validateCourseRequest(CreateCourseRequest request) {
        ArrayList<String> errorMessages = new ArrayList<>();

        // Validate course name
        String courseName = request.getCourseName();
        if (courseName == null || courseName.trim().isEmpty()) {
            errorMessages.add("Course name cannot be empty.");
        } else {
            // Check course name format
            if (!Pattern.matches("^[a-zA-Z0-9\\s]+$", courseName)) {
                errorMessages.add("Course name can only contain letters, numbers, and spaces.");
            }
            // Check course name length
            if (courseName.length() < 3 || courseName.length() > 100) {
                errorMessages.add("Course name must be between 3 and 100 characters.");
            }
        }

        // Validate start date
        Date startDate = request.getStartDate();
        if (startDate == null || startDate.before(new Date())) {
            errorMessages.add("Start date must be in the future or the present.");
        }

        // Validate end date
        Date endDate = request.getEndDate();
        if (endDate == null || endDate.before(new Date())) {
            errorMessages.add("End date must be in the future or the present.");
        }

        if (endDate != null && startDate != null && endDate.before(startDate)) {
            errorMessages.add("End date must be after the start date.");
        }


        return new ValidationResult(errorMessages);
    }
}
