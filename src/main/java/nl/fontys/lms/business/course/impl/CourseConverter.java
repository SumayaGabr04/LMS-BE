package nl.fontys.lms.business.course.impl;

import lombok.NoArgsConstructor;
import nl.fontys.lms.domain.course.Course;
import nl.fontys.lms.persistence.entity.CourseEntity;

@NoArgsConstructor
public class CourseConverter {
    public static Course convert(CourseEntity course) {
        return Course.builder()
//                .id(course.getId())
                .courseName(course.getCourseName())
                .description(course.getDescription())
                .instructor(course.getInstructor())
                .enrollmentCapacity(course.getEnrollmentCapacity())
                .startDate(course.getStartDate())
                .endDate(course.getEndDate())
                .build();
    }
}
