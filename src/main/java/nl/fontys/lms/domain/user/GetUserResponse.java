package nl.fontys.lms.domain.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
@AllArgsConstructor
public class GetUserResponse {
    private User user;

}
