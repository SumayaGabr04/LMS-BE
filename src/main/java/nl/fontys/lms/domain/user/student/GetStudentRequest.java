package nl.fontys.lms.domain.user.student;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetStudentRequest {
    private Long id; // ID of the student to retrieve
}
