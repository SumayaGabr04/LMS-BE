package nl.fontys.lms.domain.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Long id; // This can represent the ID of the created or updated user
    private String firstName;
    private String lastName;
    private String email;
}
