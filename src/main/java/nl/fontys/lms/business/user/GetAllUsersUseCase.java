package nl.fontys.lms.business.user;

import nl.fontys.lms.domain.user.GetAllUsersResponse;
import nl.fontys.lms.domain.user.UserResponse;

import java.util.ArrayList;

public interface GetAllUsersUseCase {
    GetAllUsersResponse getAllUsers();
}
