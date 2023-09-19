package nl.fontys.lms.business.user.student.impl;

import lombok.NoArgsConstructor;
import nl.fontys.lms.domain.user.student.Student;
import nl.fontys.lms.persistence.entity.StudentEntity;

@NoArgsConstructor
public class StudentConverter {
    public static Student convert(StudentEntity entity) {
        return Student.builder()
                .id(entity.getStudentId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                // Map other fields as needed
                .build();
    }
}
