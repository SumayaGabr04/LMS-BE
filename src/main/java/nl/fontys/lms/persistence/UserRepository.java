package nl.fontys.lms.persistence;

import nl.fontys.lms.domain.user.User;
import nl.fontys.lms.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByEmail(String email);

    Optional<UserEntity> findById(long userId);

    ArrayList<UserEntity> findAll();

    long count();
}
