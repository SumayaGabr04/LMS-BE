package nl.fontys.lms.business.enrollment.impl;

import lombok.AllArgsConstructor;
import nl.fontys.lms.business.enrollment.GetEnrollmentsForStudentUseCase;
import nl.fontys.lms.domain.enrollment.Enrollment;
import nl.fontys.lms.domain.enrollment.GetEnrollmentsForStudentResponse;
import nl.fontys.lms.persistence.EnrollmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
                .toList();

        return GetEnrollmentsForStudentResponse.builder()
                .studentId(studentId)
                .enrollments(enrollments)
                .build();
    }
}
