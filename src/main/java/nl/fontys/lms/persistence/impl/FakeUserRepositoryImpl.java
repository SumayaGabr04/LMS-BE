package nl.fontys.lms.persistence.impl;

import nl.fontys.lms.persistence.UserRepository;
import nl.fontys.lms.persistence.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class FakeUserRepositoryImpl implements UserRepository {
    private static long NEXT_USER_ID = 1;
    private final ArrayList<UserEntity> users = new ArrayList<>();

    @Override
    public boolean existsByEmail(String email) {
        return users.stream().anyMatch(userEntity -> userEntity.getEmail().equals(email));
    }

    @Override
    public boolean existsById(long userId) {
        return users.stream().anyMatch(userEntity -> userEntity.getUserId() == userId);
    }

    @Override
    public void deleteById(long userId) {
        users.removeIf(userEntity -> userEntity.getUserId() == userId);
    }

    @Override
    public UserEntity findById(long userId) {
        return users.stream().filter(userEntity -> userEntity.getUserId() == userId).findFirst().orElse(null);
    }

    @Override
    public ArrayList<UserEntity> findAll() {
        return new ArrayList<>(users);
    }

    @Override
    public UserEntity save(UserEntity user) {
        if (user.getUserId() == null) {
            user.setUserId(NEXT_USER_ID);
            NEXT_USER_ID++;
        }
        users.add(user);
        return user;
    }

    @Override
    public int count() {
        return users.size();
    }
}
