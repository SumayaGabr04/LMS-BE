package nl.fontys.lms.business.login;

import nl.fontys.lms.domain.login.LoginRequest;
import nl.fontys.lms.domain.login.LoginResponse;

public interface LoginUseCase {
    LoginResponse login(LoginRequest loginRequest);
}
