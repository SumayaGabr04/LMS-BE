package nl.fontys.lms.business.course.impl;

import lombok.NoArgsConstructor;
import nl.fontys.lms.business.material.impl.MaterialConverter;
import nl.fontys.lms.business.user.impl.UserConverter;
import nl.fontys.lms.domain.course.Course;
import nl.fontys.lms.domain.material.Material;
import nl.fontys.lms.domain.user.User;
import nl.fontys.lms.persistence.entity.CourseEntity;
import nl.fontys.lms.persistence.entity.CourseMaterialEntity;
import nl.fontys.lms.persistence.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
public class CourseConverter {
    public static Course convert(CourseEntity course) {
        return Course.builder()
                .id(course.getId())
                .courseName(course.getCourseName())
                .description(course.getDescription())
                .instructor(course.getInstructor())
                .enrollmentCapacity(course.getEnrollmentCapacity())
                .startDate(course.getStartDate())
                .endDate(course.getEndDate())
                .courseMaterials(new ArrayList<>(convertMaterials(course.getCourseMaterials())))
                .enrolledStudents(new ArrayList<>(convertUsers(course.getEnrolledStudents())))
                .build();
    }


    private static List<Material> convertMaterials(List<CourseMaterialEntity> courseMaterials) {
        if (courseMaterials == null) {
            return List.of();
        }

        return courseMaterials.stream()
                .map(MaterialConverter::convertMaterial)
                .collect(Collectors.toList());
    }


    private static List<User> convertUsers(List<UserEntity> enrolledStudents) {
        if (enrolledStudents == null) {
            return List.of();
        }

        return enrolledStudents.stream()
                .map(UserConverter::convert)
                .collect(Collectors.toList());
    }
}
