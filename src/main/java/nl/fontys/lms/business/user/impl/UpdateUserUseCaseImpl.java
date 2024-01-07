package nl.fontys.lms.business.user.impl;

import lombok.AllArgsConstructor;
import nl.fontys.lms.business.exception.UserNotFoundException;
import nl.fontys.lms.business.user.UpdateUserUseCase;
import nl.fontys.lms.domain.user.UpdateUserRequest;
import nl.fontys.lms.persistence.UserRepository;
import nl.fontys.lms.persistence.entity.UserEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateUserUseCaseImpl implements UpdateUserUseCase {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void updateUser(UpdateUserRequest request) {
        UserEntity existingUser = userRepository.findById(request.getId())
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + request.getId()));

        // Update user fields as needed
        existingUser.setFirstName(request.getFirstName());
        existingUser.setLastName(request.getLastName());
        existingUser.setEmail(request.getEmail());

        // Hash and salt the new password
        String hashedPassword = passwordEncoder.encode(request.getPassword());

        existingUser.setPasswordHash(hashedPassword);

        userRepository.save(existingUser);
    }
}
