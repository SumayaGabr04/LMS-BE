package nl.fontys.lms.business.user.impl;

import lombok.AllArgsConstructor;
import nl.fontys.lms.business.user.GetAllUsersUseCase;
import nl.fontys.lms.domain.user.GetAllUsersResponse;
import nl.fontys.lms.domain.user.User;
import nl.fontys.lms.domain.user.UserResponse;
import nl.fontys.lms.persistence.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GetAllUsersUseCaseImpl implements GetAllUsersUseCase {
    private final UserRepository userRepository;

    @Override
    public GetAllUsersResponse getAllUsers() {
        ArrayList<User> users = userRepository.findAll()
                .stream()
                .map(UserConverter::convert)
                .collect(Collectors.toCollection(ArrayList::new));

        return GetAllUsersResponse.builder().users(users).build();
    }
}
