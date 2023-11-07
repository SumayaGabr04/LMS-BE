package nl.fontys.lms.business.enrollment.impl;

import lombok.AllArgsConstructor;
import nl.fontys.lms.business.enrollment.GetEnrollmentsForStudentUseCase;
import nl.fontys.lms.domain.Enrollment.Enrollment;
import nl.fontys.lms.domain.Enrollment.GetEnrollmentsForStudentResponse;
import nl.fontys.lms.persistence.EnrollmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GetEnrollmentsForStudentUseCaseImpl implements GetEnrollmentsForStudentUseCase {
    private final EnrollmentRepository enrollmentRepository;

    @Override
    public GetEnrollmentsForStudentResponse getEnrollmentsForStudent(Long studentId) {
        List<Enrollment> enrollments = enrollmentRepository
                .findAllEnrollmentsForStudent(studentId)
                .stream()
                .map(EnrollmentConverter::convert)
                .collect(Collectors.toList());

        return GetEnrollmentsForStudentResponse.builder()
                .studentId(studentId)
                .enrollments(enrollments)
                .build();
    }
}
