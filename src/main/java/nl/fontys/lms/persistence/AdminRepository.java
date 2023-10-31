package nl.fontys.lms.persistence;

import nl.fontys.lms.persistence.entity.AdminEntity;

import java.util.ArrayList;

public interface AdminRepository {
    boolean existsById(long adminId);

    boolean existsByEmail(String email);

    void deleteById(long adminId);

    AdminEntity findById(long adminId);

    ArrayList<AdminEntity> findAll();

    AdminEntity save(AdminEntity admin);

    int count();
}
