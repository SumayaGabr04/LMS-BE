package nl.fontys.lms.businesss.user;

import nl.fontys.lms.business.user.impl.UserConverter;
import nl.fontys.lms.domain.user.User;
import nl.fontys.lms.persistence.entity.UserEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserConverterTest {
    @Test
    void testConvert() {
        // Arrange
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(1L);
        userEntity.setFirstName("John");
        userEntity.setLastName("Doe");
        userEntity.setEmail("john.doe@example.com");
        userEntity.setPasswordHash("hashedPassword");
        userEntity.setPasswordSalt("passwordSalt");

        // Act
        User convertedUser = UserConverter.convert(userEntity);

        // Assert
        assertEquals(userEntity.getUserId(), convertedUser.getId());
        assertEquals(userEntity.getFirstName(), convertedUser.getFirstName());
        assertEquals(userEntity.getLastName(), convertedUser.getLastName());
        assertEquals(userEntity.getEmail(), convertedUser.getEmail());
        assertEquals(userEntity.getPasswordHash(), convertedUser.getPasswordHash());
        assertEquals(userEntity.getPasswordSalt(), convertedUser.getPasswordSalt());
    }
}
