package nl.fontys.lms.business.material.impl;

import nl.fontys.lms.domain.material.Material;
import nl.fontys.lms.persistence.entity.CourseMaterialEntity;

import java.util.Base64;

public class MaterialConverter {
    public static Material convertMaterial(CourseMaterialEntity courseMaterial) {
        if (courseMaterial == null || courseMaterial.getMaterial() == null) {
            return null; // or throw an exception, depending on your requirements
        }

        // Create and return a Material object
        return new Material(courseMaterial.getId(), courseMaterial.getCourse().getId(), courseMaterial.getName(), null);
    }

    public static byte[] convertMaterial(String material) {
        if (material == null) {
            return new byte[0]; // or throw an exception, depending on your requirements
        }

        // Your logic for converting the material string to a byte[]
        // For example, you can decode it from Base64
        return Base64.getDecoder().decode(material);
    }
}
