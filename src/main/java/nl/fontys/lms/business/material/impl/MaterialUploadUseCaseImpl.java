package nl.fontys.lms.business.material.impl;

import nl.fontys.lms.business.material.MaterialUploadUseCase;
import nl.fontys.lms.domain.material.Material;
import nl.fontys.lms.persistence.CourseMaterialRepository;
import nl.fontys.lms.persistence.entity.CourseEntity;
import nl.fontys.lms.persistence.entity.CourseMaterialEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MaterialUploadUseCaseImpl implements MaterialUploadUseCase {
    private final CourseMaterialRepository materialRepository;

    public MaterialUploadUseCaseImpl(CourseMaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    @Override
    public void uploadMaterial(Material material) {
        try {
            // Convert MultipartFile to byte[]
            byte[] materialBytes = material.getMaterialFile().getBytes();
            String title = material.getTitle();

            // Save the material to the repository
            CourseMaterialEntity courseMaterialEntity = CourseMaterialEntity.builder()
                    .course(CourseEntity.builder().id(material.getCourseId()).build()) // Provide an instance of CourseEntity
                    .material(materialBytes)
                    .name(title)
                    .build();

            MaterialConverter.convertMaterial(materialRepository.save(courseMaterialEntity));
        } catch (IOException e) {
            throw new MaterialUploadException("Error uploading material", e);
        }
    }
}
