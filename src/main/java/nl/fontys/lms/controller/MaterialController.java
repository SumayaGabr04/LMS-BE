package nl.fontys.lms.controller;

import jakarta.annotation.security.RolesAllowed;
import nl.fontys.lms.business.material.GetMaterialUseCase;
import nl.fontys.lms.business.material.MaterialUploadUseCase;
import nl.fontys.lms.domain.material.Material;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
@RequestMapping("/material")
@CrossOrigin(origins = "http://localhost:5173")
public class MaterialController {
    private final MaterialUploadUseCase materialUploadUseCase;
    private final GetMaterialUseCase getMaterialUseCase;

    @Autowired
    public MaterialController(
            MaterialUploadUseCase materialUploadUseCase,
            GetMaterialUseCase getMaterialUseCase
    ) {
        this.materialUploadUseCase = materialUploadUseCase;
        this.getMaterialUseCase = getMaterialUseCase;
    }

    @RolesAllowed("TEACHER")
    @PostMapping("/upload")
    public ResponseEntity<String> uploadMaterial(
            @RequestParam("courseId") Long courseId,
            @RequestParam("title") String title,
            @RequestParam("materialFile") MultipartFile materialFile
    ) {
        try {
            Material material = Material.builder()
                    .courseId(courseId)
                    .title(title) // Set the title here
                    .materialFile(materialFile)
                    .build();

            materialUploadUseCase.uploadMaterial(material);
            return ResponseEntity.ok("Material uploaded successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error uploading material: " + e.getMessage());
        }
    }
    @GetMapping("/{materialId}")
    public ResponseEntity<Material> getMaterialById(@PathVariable Long materialId) {
        Optional<Material> material = getMaterialUseCase.getMaterialById(materialId);
        return material.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
