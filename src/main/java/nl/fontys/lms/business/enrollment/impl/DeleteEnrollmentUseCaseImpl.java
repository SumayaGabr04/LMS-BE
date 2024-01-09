package nl.fontys.lms.business.enrollment.impl;

import nl.fontys.lms.business.enrollment.DeleteEnrollmentUseCase;
import nl.fontys.lms.persistence.EnrollmentRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class DeleteEnrollmentUseCaseImpl implements DeleteEnrollmentUseCase {
    private final EnrollmentRepository enrollmentRepository;

    public DeleteEnrollmentUseCaseImpl(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    @Override
    public void deleteEnrollment(Long enrollmentId) {
        enrollmentRepository.deleteById(enrollmentId);
    }

    @Scheduled(cron = "0 0 0 * * *") // Run daily at midnight
    public void deleteExpiredEnrollments() {
        enrollmentRepository.deleteExpiredEnrollments();
    }
}
