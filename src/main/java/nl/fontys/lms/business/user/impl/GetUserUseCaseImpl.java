package nl.fontys.lms.business.user.impl;

import lombok.AllArgsConstructor;
import nl.fontys.lms.business.exception.UserNotFoundException;
import nl.fontys.lms.business.user.GetUserUseCase;
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
    public Optional<User> getUserById(Long userId) {
        Optional<UserEntity> userEntityOptional = userRepository.findById(userId);

        if (userEntityOptional == null) {
            throw new UserNotFoundException(); // Create this exception class
        }

        UserEntity userEntity = userEntityOptional.get();
        return Optional.of(UserConverter.convert(userEntity));    }
}
