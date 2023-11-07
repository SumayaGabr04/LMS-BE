package nl.fontys.lms.business.enrollment.impl;

import lombok.AllArgsConstructor;
import nl.fontys.lms.business.enrollment.GetEnrollmentsForCourseUseCase;
import nl.fontys.lms.domain.Enrollment.Enrollment;
import nl.fontys.lms.domain.Enrollment.GetEnrollmentsForCourseResponse;
import nl.fontys.lms.persistence.EnrollmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
                .collect(Collectors.toList());

        return GetEnrollmentsForCourseResponse.builder()
                .courseId(courseId)
                .enrollments(enrollments)
                .build();
    }
}
