package nl.fontys.lms.business.user.impl;

import lombok.AllArgsConstructor;
import nl.fontys.lms.business.course.CreateCourseUseCase;
import nl.fontys.lms.business.exception.EmailAlreadyExists;
import nl.fontys.lms.business.user.CreateUserUseCase;
import nl.fontys.lms.domain.user.CreateResponse;
import nl.fontys.lms.domain.user.UserRequest;
import nl.fontys.lms.persistence.UserRepository;
import nl.fontys.lms.persistence.entity.UserEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateUserUseCaseImpl implements CreateUserUseCase {
    private final UserRepository userRepository;

    @Override
    public CreateResponse createUser(UserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExists();
        }
        UserEntity userEntity = saveNewUser(request);

        return CreateResponse.builder()
                .id(userEntity.getUserId())
                .build();
    }

    private UserEntity saveNewUser(UserRequest request) {
        UserEntity newUser = UserEntity.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(request.getPassword())
                .build();

        return userRepository.save(newUser);
    }
}
