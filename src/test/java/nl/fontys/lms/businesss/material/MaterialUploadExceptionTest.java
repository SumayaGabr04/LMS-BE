package nl.fontys.lms.businesss.material;

import nl.fontys.lms.business.material.impl.MaterialUploadException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class MaterialUploadExceptionTest {
    @Test
    public void testConstructorWithMessage() {
        // Arrange
        String errorMessage = "Test error message";

        // Act
        MaterialUploadException exception = new MaterialUploadException(errorMessage);

        // Assert
        assertEquals(errorMessage, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    public void testConstructorWithMessageAndCause() {
        // Arrange
        String errorMessage = "Test error message";
        Throwable cause = new RuntimeException("Test cause");

        // Act
        MaterialUploadException exception = new MaterialUploadException(errorMessage, cause);

        // Assert
        assertEquals(errorMessage, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }
}
