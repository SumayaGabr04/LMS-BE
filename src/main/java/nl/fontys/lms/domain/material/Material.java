package nl.fontys.lms.domain.material;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Material {
    long id;
    private Long courseId;
    private String title;
    private MultipartFile materialFile;
}
