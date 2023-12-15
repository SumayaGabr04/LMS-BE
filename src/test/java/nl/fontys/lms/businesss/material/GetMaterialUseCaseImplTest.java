package nl.fontys.lms.businesss.material;

import nl.fontys.lms.business.material.impl.GetMaterialUseCaseImpl;
import nl.fontys.lms.domain.material.Material;
import nl.fontys.lms.persistence.CourseMaterialRepository;
import nl.fontys.lms.persistence.entity.CourseMaterialEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class GetMaterialUseCaseImplTest {
    @Mock
    private CourseMaterialRepository materialRepository;

    @InjectMocks
    private GetMaterialUseCaseImpl getMaterialUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getMaterialById_shouldReturnMaterial() {
        // Arrange
        Long materialId = 1L;

        // Mock the behavior of materialRepository.findById()
        CourseMaterialEntity courseMaterialEntity = new CourseMaterialEntity();
        courseMaterialEntity.setId(materialId);
        when(materialRepository.findById(materialId)).thenReturn(Optional.of(courseMaterialEntity));

        // Act
        Optional<Material> result = getMaterialUseCase.getMaterialById(materialId);


    }

    @Test
    void getMaterialById_shouldReturnEmptyOptionalWhenMaterialNotFound() {
        // Arrange
        Long materialId = 1L;

        // Mock the behavior of materialRepository.findById()
        when(materialRepository.findById(materialId)).thenReturn(Optional.empty());

        // Act
        Optional<Material> result = getMaterialUseCase.getMaterialById(materialId);

        // Assert
        assertTrue(result.isEmpty());
    }
}
