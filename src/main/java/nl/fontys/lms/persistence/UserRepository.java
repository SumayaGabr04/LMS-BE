package nl.fontys.lms.persistence;

import nl.fontys.lms.domain.user.User;
import nl.fontys.lms.persistence.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

public interface UserRepository {
    boolean existsByEmail(String email);

    boolean existsById(long userId);

    void deleteById(long userId);

    UserEntity findById(long userId);

    ArrayList<UserEntity> findAll();

    UserEntity save(UserEntity user);

    int count();
}
