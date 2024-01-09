package nl.fontys.lms.business.enrollment.impl;

import nl.fontys.lms.business.course.impl.CourseConverter;
import nl.fontys.lms.domain.enrollment.Enrollment;
import nl.fontys.lms.persistence.entity.EnrollmentEntity;

public class EnrollmentConverter {
    private EnrollmentConverter() {
        // private constructor to hide the implicit public one
    }
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
