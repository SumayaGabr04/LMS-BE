package nl.fontys.lms.business.user.teacher.impl;

import lombok.NoArgsConstructor;
import nl.fontys.lms.business.course.impl.CourseConverter;
import nl.fontys.lms.domain.user.teacher.Teacher;
import nl.fontys.lms.persistence.entity.TeacherEntity;

import java.util.ArrayList;
import java.util.stream.Collectors;

@NoArgsConstructor
public class TeacherConverter {
    public static Teacher convert(TeacherEntity entity) {
            return Teacher.builder()
                .id(entity.getTeacherId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .coursesTaught(new ArrayList<>(entity.getCoursesTaught().stream()
                        .map(CourseConverter::convert)
                        .collect(Collectors.toList())))
                .department(entity.getDepartment())
                .hireDate(entity.getHireDate())
                .build();
    }
}
