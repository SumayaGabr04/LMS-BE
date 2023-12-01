package nl.fontys.lms.businesss.material;

import nl.fontys.lms.business.material.MaterialConverter;
import nl.fontys.lms.persistence.entity.CourseMaterialEntity;
import org.junit.jupiter.api.Test;

import java.util.Base64;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class MaterialConverterTest {
    @Test
    public void testConvertMaterial() {
        // Arrange
        MaterialConverter materialConverter = new MaterialConverter();
        CourseMaterialEntity courseMaterial = new CourseMaterialEntity();
        String originalMaterial = "This is a test material";
        courseMaterial.setMaterial(originalMaterial.getBytes());

        // Act
        String convertedMaterial = MaterialConverter.convertMaterial(courseMaterial);

        // Assert
        assertEquals(Base64.getEncoder().encodeToString(originalMaterial.getBytes()), convertedMaterial);
    }

    @Test
    public void testConvertMaterialWithNullInput() {
        // Arrange
        MaterialConverter materialConverter = new MaterialConverter();

        // Act
        String convertedMaterial = MaterialConverter.convertMaterial(null);

        // Assert
        assertNull(convertedMaterial);
    }

    @Test
    public void testConvertMaterialWithNullMaterial() {
        // Arrange
        MaterialConverter materialConverter = new MaterialConverter();
        CourseMaterialEntity courseMaterial = new CourseMaterialEntity();

        // Act
        String convertedMaterial = MaterialConverter.convertMaterial(courseMaterial);

        // Assert
        assertNull(convertedMaterial);
    }
}
