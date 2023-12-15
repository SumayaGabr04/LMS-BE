package nl.fontys.lms.business.material.impl;

import nl.fontys.lms.business.material.GetMaterialUseCase;
import nl.fontys.lms.domain.material.Material;
import nl.fontys.lms.persistence.CourseMaterialRepository;
import nl.fontys.lms.persistence.entity.CourseMaterialEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetMaterialUseCaseImpl implements GetMaterialUseCase {
    private final CourseMaterialRepository materialRepository;

    @Autowired
    public GetMaterialUseCaseImpl(CourseMaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    @Override
    public Optional<Material> getMaterialById(Long materialId) {
        Optional<CourseMaterialEntity> optionalEntity = materialRepository.findById(materialId);

        // Map CourseMaterialEntity to Material using MaterialConverter
        return optionalEntity.map(MaterialConverter::convertMaterial);
    }
}
