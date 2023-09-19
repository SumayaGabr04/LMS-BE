package nl.fontys.lms.domain.user.teacher;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetTeacherRequest {
    private Long id; // ID of the teacher to retrieve
}
