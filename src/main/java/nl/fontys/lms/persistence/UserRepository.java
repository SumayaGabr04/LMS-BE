package nl.fontys.lms.persistence;

import nl.fontys.lms.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByEmail(String email);
    UserEntity findByEmail(String email);

    Optional<UserEntity> findById(long userId);

    ArrayList<UserEntity> findAll();

    long count();
}
