package nl.fontys.lms.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
//@Builder
//@AllArgsConstructor
@SuperBuilder
@NoArgsConstructor
public class UserEntity {
    private Long userId;

    private String firstName;
    private String lastName;
    private String email;
    private String password;
}