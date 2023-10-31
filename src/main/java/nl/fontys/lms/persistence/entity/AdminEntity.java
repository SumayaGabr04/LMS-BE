package nl.fontys.lms.persistence.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;

@Data
@SuperBuilder
@NoArgsConstructor
public class AdminEntity extends UserEntity{
    private Long adminId;

    private String department;
    private String hireDate;
}
