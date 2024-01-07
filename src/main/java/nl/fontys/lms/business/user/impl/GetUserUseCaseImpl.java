package nl.fontys.lms.business.user.impl;

import lombok.AllArgsConstructor;
import nl.fontys.lms.business.exception.UserNotFoundException;
import nl.fontys.lms.business.user.GetUserUseCase;
import nl.fontys.lms.domain.user.GetUserRequest;
import nl.fontys.lms.domain.user.GetUserResponse;
import nl.fontys.lms.domain.user.User;
import nl.fontys.lms.persistence.UserRepository;
import nl.fontys.lms.persistence.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GetUserUseCaseImpl implements GetUserUseCase {
    private final UserRepository userRepository;

    @Override
    public GetUserResponse getUserById(GetUserRequest request) {
        Optional<UserEntity> userEntityOptional = userRepository.findById(request.getId());

        if (userEntityOptional.isEmpty()) {
            throw new UserNotFoundException();
        }

        UserEntity userEntity = userEntityOptional.get();
        User user = UserConverter.convert(userEntity);

        return GetUserResponse.builder().user(user).build();
    }
}
