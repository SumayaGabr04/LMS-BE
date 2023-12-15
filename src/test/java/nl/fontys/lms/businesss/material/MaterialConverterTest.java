package nl.fontys.lms.businesss.material;

import nl.fontys.lms.business.material.impl.MaterialConverter;
import nl.fontys.lms.domain.material.Material;
import nl.fontys.lms.persistence.entity.CourseEntity;
import nl.fontys.lms.persistence.entity.CourseMaterialEntity;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class MaterialConverterTest {
    @Test
    public void testConvertMaterial() throws IOException {
        // Arrange
        MaterialConverter materialConverter = new MaterialConverter();
        CourseMaterialEntity courseMaterial = new CourseMaterialEntity();
        String originalMaterial = "This is a test material";
        courseMaterial.setId(1L);  // Set an example ID
        courseMaterial.setCourse(new CourseEntity());  // Set an example CourseEntity
        courseMaterial.setName("MaterialName");  // Set an example name

        // Create a MockMultipartFile with the content of the original material
        MockMultipartFile mockMultipartFile = new MockMultipartFile(
                "materialFile",      // field name
                "test.txt",          // original filename
                "text/plain",         // content type
                originalMaterial.getBytes());  // content as byte[]

        // Set the byte array directly
        try {
            courseMaterial.setMaterial(mockMultipartFile.getBytes());
        } catch (IOException e) {
            // Handle the IOException (e.g., log the error or throw it)
            throw e;
        }

        // Act
        Material convertedMaterial = MaterialConverter.convertMaterial(courseMaterial);

        // Assert
        assertEquals(courseMaterial.getId(), convertedMaterial.getId());
        assertEquals(courseMaterial.getCourse().getId(), convertedMaterial.getCourseId());
        assertEquals(courseMaterial.getName(), convertedMaterial.getTitle());


        // Convert the material back to a string for comparison
//        assertEquals(originalMaterial, convertedMaterial.getMaterialFile());
    }

    @Test
    public void testConvertMaterialWithNullInput() {
        // Arrange
        MaterialConverter materialConverter = new MaterialConverter();

        // Act
        Material convertedMaterial = MaterialConverter.convertMaterial((CourseMaterialEntity) null);

        // Assert
        assertNull(convertedMaterial);
    }

    @Test
    public void testConvertMaterialWithNullMaterial() {
        // Arrange
        MaterialConverter materialConverter = new MaterialConverter();
        CourseMaterialEntity courseMaterial = new CourseMaterialEntity();

        // Act
        Material convertedMaterial = MaterialConverter.convertMaterial(courseMaterial);

        // Assert
        assertNull(convertedMaterial);
    }
}
