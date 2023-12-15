package nl.fontys.lms.business.material;

import nl.fontys.lms.domain.material.Material;

import java.util.Optional;

public interface GetMaterialUseCase {
    Optional<Material> getMaterialById(Long materialId);
}