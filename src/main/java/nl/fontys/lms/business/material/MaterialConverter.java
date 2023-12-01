package nl.fontys.lms.business.material;

import nl.fontys.lms.persistence.entity.CourseMaterialEntity;

import java.util.Base64;

public class MaterialConverter { //TODO; finish implementation
    public static String convertMaterial(CourseMaterialEntity courseMaterial) {
        if (courseMaterial == null || courseMaterial.getMaterial() == null) {
            return null; // or throw an exception, depending on your requirements
        }

        // Your logic for converting the material byte[] to a string
        // For example, you can convert it to Base64
        return Base64.getEncoder().encodeToString(courseMaterial.getMaterial());
    }
}
