package nl.fontys.lms.business.user.admin.impl;

import lombok.NoArgsConstructor;
import nl.fontys.lms.domain.user.admin.Admin;
import nl.fontys.lms.persistence.entity.AdminEntity;

@NoArgsConstructor
public class AdminConverter {
    public static Admin convert(AdminEntity entity) {
        if (entity == null) {
            return null;
        }

        return Admin.builder()
                .id(entity.getUserId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .email(entity.getEmail())
                .passwordHash(entity.getPasswordHash())
                .passwordSalt(entity.getPasswordSalt())
                .department(entity.getDepartment())
                .hireDate(entity.getHireDate())
                .build();
    }
}
