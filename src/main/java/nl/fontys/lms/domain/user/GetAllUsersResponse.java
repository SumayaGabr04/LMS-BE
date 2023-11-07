package nl.fontys.lms.domain.user;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;

@Data
@Builder
public class GetAllUsersResponse {
    private ArrayList<User> users;
}
