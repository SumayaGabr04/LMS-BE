package nl.fontys.lms.business.user.impl;

import lombok.AllArgsConstructor;
import nl.fontys.lms.business.exception.UserNotFoundException;
import nl.fontys.lms.business.user.DeleteUserUseCase;
import nl.fontys.lms.persistence.UserRepository;
import nl.fontys.lms.persistence.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class DeleteUserUseCaseImpl implements DeleteUserUseCase {
    private final UserRepository userRepository;

    @Override
    public void deleteUser(Long userId) {
        Optional<UserEntity> existingUserOptional = userRepository.findById(userId);

        if (existingUserOptional.isEmpty()) {
            throw new UserNotFoundException();
        }

        UserEntity existingUser = existingUserOptional.get();

        userRepository.deleteById(userId);
    }
}
