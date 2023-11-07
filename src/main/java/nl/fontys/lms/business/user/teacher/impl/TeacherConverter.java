package nl.fontys.lms.business.user.teacher.impl;

import lombok.NoArgsConstructor;
import nl.fontys.lms.business.course.impl.CourseConverter;
import nl.fontys.lms.domain.user.teacher.Teacher;
import nl.fontys.lms.persistence.entity.TeacherEntity;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.text.SimpleDateFormat;
import java.util.Date;

@NoArgsConstructor
public class TeacherConverter {
    public static Teacher convert(TeacherEntity entity) {
        if (entity == null) {
            return null;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Define the date format you're using
        String hireDateStr = dateFormat.format(entity.getHireDate());

        return Teacher.builder()
                .id(entity.getUserId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .email(entity.getEmail())
                .passwordHash(entity.getPasswordHash())
                .passwordSalt(entity.getPasswordSalt())
//                .coursesTaught(entity.getCoursesTaught() != null
//                        ? new ArrayList<>(entity.getCoursesTaught().stream()
//                        .map(CourseConverter::convert)
//                        .collect(Collectors.toList()))
//                        : new ArrayList<>())
                .department(entity.getDepartment())
                .hireDate(hireDateStr)
                .build();

    }
}
