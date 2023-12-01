package nl.fontys.lms.domain.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private String role;
    private String firstName;
    private String lastName;
    private String email;

//    // Additional fields for students
//    private String major;
//    // Additional fields for teachers
//    private String department;
//    private Date hireDate;
}
