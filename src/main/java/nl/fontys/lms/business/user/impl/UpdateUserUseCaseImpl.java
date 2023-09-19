package nl.fontys.lms.business.user.impl;

import lombok.AllArgsConstructor;
import nl.fontys.lms.business.exception.UserNotFoundException;
import nl.fontys.lms.business.user.UpdateUserUseCase;
import nl.fontys.lms.domain.user.UpdateUserRequest;
import nl.fontys.lms.domain.user.UserResponse;
import nl.fontys.lms.persistence.UserRepository;
import nl.fontys.lms.persistence.entity.UserEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateUserUseCaseImpl implements UpdateUserUseCase {
    private final UserRepository userRepository;

    @Override
    public void updateUser(UpdateUserRequest request) {
        UserEntity existingUser = userRepository.findById(request.getId());
        if (existingUser == null) {
            throw new UserNotFoundException();
        }

        // Update user fields as needed
        existingUser.setFirstName(request.getFirstName());
        existingUser.setLastName(request.getLastName());
        existingUser.setEmail(request.getEmail());
        existingUser.setPassword(request.getPassword());

        userRepository.save(existingUser);
    }
}
