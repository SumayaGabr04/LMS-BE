package nl.fontys.lms.persistence;

import nl.fontys.lms.persistence.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface AdminRepository extends JpaRepository<AdminEntity, Long> {
    boolean existsByEmail(String email);

    ArrayList<AdminEntity> findAll();

    long count();
}
