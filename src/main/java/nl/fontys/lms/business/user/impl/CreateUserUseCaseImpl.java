package nl.fontys.lms.business.user.impl;

import lombok.AllArgsConstructor;
import nl.fontys.lms.business.exception.EmailAlreadyExists;
import nl.fontys.lms.business.user.CreateUserUseCase;
import nl.fontys.lms.domain.user.CreateUserResponse;
import nl.fontys.lms.domain.user.CreateUserRequest;
import nl.fontys.lms.persistence.UserRepository;
import nl.fontys.lms.persistence.entity.UserEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateUserUseCaseImpl implements CreateUserUseCase {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public CreateUserResponse createUser(CreateUserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExists();
        }
        UserEntity userEntity = saveNewUser(request);

        return CreateUserResponse.builder()
                .id(userEntity.getUserId())
                .build();
    }

    private UserEntity saveNewUser(CreateUserRequest request) {
        // Hash and salt the password before saving
        String hashedPassword = passwordEncoder.encode(request.getPassword());

        UserEntity newUser = UserEntity.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .passwordHash(hashedPassword)
                .role(request.getRole().getValue())
                .build();

        return userRepository.save(newUser);
    }
}
