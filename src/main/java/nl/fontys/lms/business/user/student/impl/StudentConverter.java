package nl.fontys.lms.business.user.student.impl;

import lombok.NoArgsConstructor;
import nl.fontys.lms.business.course.impl.CourseConverter;
import nl.fontys.lms.domain.user.student.Student;
import nl.fontys.lms.persistence.entity.StudentEntity;

import java.util.ArrayList;
import java.util.stream.Collectors;

@NoArgsConstructor
public class StudentConverter {
    public static Student convert(StudentEntity entity) {
        return Student.builder()
                .id(entity.getUserId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .email(entity.getEmail())
                .passwordHash(entity.getPasswordHash())
                .passwordSalt(entity.getPasswordSalt())
                .coursesEnrolled(entity.getCoursesEnrolled() != null
                        ? new ArrayList<>(entity.getCoursesEnrolled().stream()
                        .map(CourseConverter::convert)
                        .collect(Collectors.toList()))
                        : new ArrayList<>()) // Initialize with an empty list if coursesTaught is null
                .major(entity.getMajor())
                .build();
    }
}
