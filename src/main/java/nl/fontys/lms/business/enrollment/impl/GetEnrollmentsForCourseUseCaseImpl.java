package nl.fontys.lms.business.enrollment.impl;

import lombok.AllArgsConstructor;
import nl.fontys.lms.business.enrollment.GetEnrollmentsForCourseUseCase;
import nl.fontys.lms.domain.enrollment.Enrollment;
import nl.fontys.lms.domain.enrollment.GetEnrollmentsForCourseResponse;
import nl.fontys.lms.persistence.EnrollmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetEnrollmentsForCourseUseCaseImpl implements GetEnrollmentsForCourseUseCase {
    private final EnrollmentRepository enrollmentRepository;

    @Override
    public GetEnrollmentsForCourseResponse getEnrollmentsForCourse(Long courseId) {
        List<Enrollment> enrollments = enrollmentRepository
                .findAllEnrollmentsForCourse(courseId)
                .stream()
                .map(EnrollmentConverter::convert)
                .toList();

        return GetEnrollmentsForCourseResponse.builder()
                .courseId(courseId)
                .enrollments(enrollments)
                .build();
    }
}
