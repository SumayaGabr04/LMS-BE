package nl.fontys.lms.business.enrollment.impl;

import lombok.AllArgsConstructor;
import nl.fontys.lms.business.enrollment.CreateEnrollmentUseCase;
import nl.fontys.lms.domain.Enrollment.CreateEnrollmentRequest;
import nl.fontys.lms.domain.Enrollment.CreateEnrollmentResponse;
import nl.fontys.lms.persistence.EnrollmentRepository;
import nl.fontys.lms.persistence.entity.CourseEntity;
import nl.fontys.lms.persistence.entity.EnrollmentEntity;
import nl.fontys.lms.persistence.entity.StudentEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

@Service
@AllArgsConstructor
public class CreateEnrollmentUseCaseImpl implements CreateEnrollmentUseCase {
    private final EnrollmentRepository enrollmentRepository;

    @Override
    public CreateEnrollmentResponse createEnrollment(CreateEnrollmentRequest request) {
        // Check if the student is already enrolled in the course
        boolean isStudentEnrolled = enrollmentRepository.isStudentEnrolledInCourse(request.getStudentId(), request.getCourseId());
        if (isStudentEnrolled) {
            return CreateEnrollmentResponse.builder()
                    .errorMessages(new ArrayList<>(Collections.singletonList("Student is already enrolled in the course")))
                    .build();
        }

        // Create and save the new enrollment
        EnrollmentEntity enrollmentEntity = saveNewEnrollment(request);

        if (enrollmentEntity != null) {
            return CreateEnrollmentResponse.builder()
                    .enrollmentId(enrollmentEntity.getId())
                    .build();
        } else {
            // Handle the case where enrollmentEntity is null
            return CreateEnrollmentResponse.builder()
                    .errorMessages(new ArrayList<>(Collections.singletonList("Failed to create the enrollment")))
                    .build();
        }
    }

    private EnrollmentEntity saveNewEnrollment(CreateEnrollmentRequest request) {
        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setId(request.getCourseId());

        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setUserId(request.getStudentId());

        EnrollmentEntity newEnrollment = EnrollmentEntity.builder()
                .course(courseEntity)
                .student(studentEntity)
                .enrollmentDate(new Date()) // Set the enrollment date to the current date
                .build();

        return enrollmentRepository.save(newEnrollment);
    }
}