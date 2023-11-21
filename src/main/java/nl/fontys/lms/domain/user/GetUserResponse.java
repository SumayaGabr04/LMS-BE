package nl.fontys.lms.domain.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Optional;

@Data
@Builder
@AllArgsConstructor
public class GetUserResponse {
    private final Optional<User> user;

}
