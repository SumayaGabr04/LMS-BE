package nl.fontys.lms.businesss.material;

import nl.fontys.lms.business.material.MaterialUploadUseCase;
import nl.fontys.lms.domain.material.Material;
import nl.fontys.lms.persistence.CourseMaterialRepository;
import nl.fontys.lms.persistence.entity.CourseMaterialEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class MaterialUploadUseCaseImplTest {

    @MockBean
    private CourseMaterialRepository materialRepository;

    @Autowired
    private MaterialUploadUseCase materialUploadUseCase;

    @Test
    void uploadMaterial_shouldSaveMaterialToRepository() throws IOException {
        // Arrange
        String title = "Test Material";
        Long courseId = 1L;

        Material material = new Material();
        material.setTitle(title);
        material.setCourseId(courseId);

        // Mock MultipartFile bytes
        byte[] mockFileBytes = "This is a test material".getBytes();

        // Mock the behavior of material.getMaterialFile().getBytes()
        MockMultipartFile mockMultipartFile = new MockMultipartFile(
                "materialFile",
                "test.txt",
                "text/plain",
                mockFileBytes
        );
        material.setMaterialFile(mockMultipartFile);

        // Act
        materialUploadUseCase.uploadMaterial(material);

        // Assert
        // Verify that materialRepository.save() is called with the expected arguments
        verify(materialRepository, times(1)).save(any(CourseMaterialEntity.class));
    }
}
