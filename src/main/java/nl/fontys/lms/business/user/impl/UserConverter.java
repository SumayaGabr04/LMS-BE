package nl.fontys.lms.business.user.impl;

import nl.fontys.lms.domain.user.User;
import nl.fontys.lms.persistence.entity.UserEntity;

import java.util.Optional;

public class UserConverter {
    private UserConverter() {
        // private constructor to hide the implicit public one
    }

    public static User convert(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }

        return User.builder()
                .id(userEntity.getUserId())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .email(userEntity.getEmail())
                .build();
    }

    public static User convert(Optional<UserEntity> userEntityOptional) {
        return userEntityOptional.map(UserConverter::convert).orElse(null);
    }

    public static UserEntity convertToEntity(User user) {
        if (user == null) {
            return null;
        }

        return UserEntity.builder()
                .userId(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();
    }
}
