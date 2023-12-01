package nl.fontys.lms.business.enrollment.impl;

import lombok.NoArgsConstructor;
import nl.fontys.lms.business.course.impl.CourseConverter;
import nl.fontys.lms.domain.Enrollment.Enrollment;
import nl.fontys.lms.persistence.entity.EnrollmentEntity;

@NoArgsConstructor
public class EnrollmentConverter {
    public static Enrollment convert(EnrollmentEntity enrollmentEntity) {
        if (enrollmentEntity == null) {
            return null;
        }

        return Enrollment.builder()
                .enrollmentId(enrollmentEntity.getId())
                .course(CourseConverter.convert(enrollmentEntity.getCourse())) // Use CourseConverter
                .studentId(enrollmentEntity.getStudent().getUserId())
                .enrollmentDate(enrollmentEntity.getEnrollmentDate())
                .build();
    }
}
