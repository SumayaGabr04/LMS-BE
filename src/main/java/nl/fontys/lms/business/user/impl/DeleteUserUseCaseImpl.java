package nl.fontys.lms.business.user.impl;

import lombok.AllArgsConstructor;
import nl.fontys.lms.business.exception.UserNotFoundException;
import nl.fontys.lms.business.user.DeleteUserUseCase;
import nl.fontys.lms.persistence.UserRepository;
import nl.fontys.lms.persistence.entity.UserEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteUserUseCaseImpl implements DeleteUserUseCase {
    private final UserRepository userRepository;

    @Override
    public void deleteUser(Long userId) {
        UserEntity existingUser = userRepository.findById(userId);
        if (existingUser == null) {
            throw new UserNotFoundException(); // Create this exception class
        }

        userRepository.deleteById(userId);
    }
}
