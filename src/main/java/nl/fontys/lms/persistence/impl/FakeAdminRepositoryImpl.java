package nl.fontys.lms.persistence.impl;

import nl.fontys.lms.persistence.AdminRepository;
import nl.fontys.lms.persistence.entity.AdminEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class FakeAdminRepositoryImpl implements AdminRepository {
    private static long NEXT_ADMIN_ID = 1;
    private final ArrayList<AdminEntity> admins;

    public FakeAdminRepositoryImpl() {
        this.admins = new ArrayList<>();
    }

    @Override
    public boolean existsById(long adminId) {
        return admins.stream().anyMatch(adminEntity -> adminEntity.getAdminId() == adminId);
    }

    @Override
    public boolean existsByEmail(String email) {
        return admins.stream().anyMatch(adminEntity -> adminEntity.getEmail().equals(email));
    }


    @Override
    public void deleteById(long adminId) {
        admins.removeIf(adminEntity -> adminEntity.getAdminId() == adminId);
    }

    @Override
    public AdminEntity findById(long adminId) {
        return admins.stream().filter(adminEntity -> adminEntity.getAdminId() == adminId).findFirst().orElse(null);
    }

    @Override
    public ArrayList<AdminEntity> findAll() {
        return new ArrayList<>(admins);
    }

    @Override
    public AdminEntity save(AdminEntity admin) {
        if (admin.getAdminId() == null) {
            admin.setAdminId(NEXT_ADMIN_ID);
            NEXT_ADMIN_ID++;
        }
        admins.add(admin);
        return admin;
    }

    @Override
    public int count() {
        return admins.size();
    }

}
