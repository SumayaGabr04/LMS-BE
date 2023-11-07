package nl.fontys.lms.business.enrollment.impl;

import lombok.NoArgsConstructor;
import nl.fontys.lms.domain.Enrollment.Enrollment;
import nl.fontys.lms.persistence.entity.EnrollmentEntity;

@NoArgsConstructor
public class EnrollmentConverter {
    public static Enrollment convert(EnrollmentEntity enrollmentEntity) {
        return Enrollment.builder()
//                .id(enrollmentEntity.getId())
                .courseId(enrollmentEntity.getCourse().getId())
                .studentId(enrollmentEntity.getStudent().getUserId())
                .enrollmentDate(enrollmentEntity.getEnrollmentDate())
                .build();
    }
}
