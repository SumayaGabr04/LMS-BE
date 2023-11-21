package nl.fontys.lms.business.user.impl;

import lombok.NoArgsConstructor;
import nl.fontys.lms.domain.user.User;
import nl.fontys.lms.persistence.entity.UserEntity;

@NoArgsConstructor
public class UserConverter {

    public static User convert(UserEntity userEntity) {
        return User.builder()
                .id(userEntity.getUserId())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .email(userEntity.getEmail())
                .build();
    }
    public static UserEntity convertToEntity(User user) {
        return UserEntity.builder()
                .userId(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();
    }
}
