package nl.fontys.lms.business.user.teacher.impl;

import lombok.NoArgsConstructor;
import nl.fontys.lms.domain.user.teacher.Teacher;
import nl.fontys.lms.persistence.entity.TeacherEntity;

@NoArgsConstructor
public class TeacherConverter {
    public static Teacher convert(TeacherEntity entity) {
        return Teacher.builder()
                .id(entity.getTeacherId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                // Map other fields as needed
                .build();
    }
}
