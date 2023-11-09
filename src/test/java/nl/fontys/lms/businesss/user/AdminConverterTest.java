package nl.fontys.lms.businesss.user;

import nl.fontys.lms.business.user.admin.impl.AdminConverter;
import nl.fontys.lms.domain.user.admin.Admin;
import nl.fontys.lms.persistence.entity.AdminEntity;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class AdminConverterTest {
    @Test
    void testConvert() {
        // Arrange
        AdminEntity adminEntity = new AdminEntity();
        adminEntity.setUserId(1L);
        adminEntity.setFirstName("John");
        adminEntity.setLastName("Doe");
        adminEntity.setEmail("john.doe@example.com");
        adminEntity.setPasswordHash("hashedPassword");
        adminEntity.setPasswordSalt("passwordSalt");
        adminEntity.setDepartment("IT");
        adminEntity.setHireDate(new Date());

        // Act
        Admin convertedAdmin = AdminConverter.convert(adminEntity);

        // Assert
        assertEquals(adminEntity.getUserId(), convertedAdmin.getId());
        assertEquals(adminEntity.getFirstName(), convertedAdmin.getFirstName());
        assertEquals(adminEntity.getLastName(), convertedAdmin.getLastName());
        assertEquals(adminEntity.getEmail(), convertedAdmin.getEmail());
        assertEquals(adminEntity.getPasswordHash(), convertedAdmin.getPasswordHash());
        assertEquals(adminEntity.getPasswordSalt(), convertedAdmin.getPasswordSalt());
        assertEquals(adminEntity.getDepartment(), convertedAdmin.getDepartment());
        assertEquals(adminEntity.getHireDate(), convertedAdmin.getHireDate());
    }

    @Test
    void testConvertWithNullEntity() {
        // Arrange
        AdminEntity adminEntity = null;

        // Act
        Admin convertedAdmin = AdminConverter.convert(adminEntity);

        // Assert
        assertNull(convertedAdmin);
    }
}
