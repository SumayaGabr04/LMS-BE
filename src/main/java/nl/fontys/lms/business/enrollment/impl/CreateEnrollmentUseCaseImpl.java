package nl.fontys.lms.business.enrollment.impl;

import lombok.AllArgsConstructor;
import nl.fontys.lms.business.enrollment.CreateEnrollmentUseCase;
import nl.fontys.lms.business.user.impl.UserConverter;
import nl.fontys.lms.domain.enrollment.CreateEnrollmentRequest;
import nl.fontys.lms.domain.enrollment.CreateEnrollmentResponse;
import nl.fontys.lms.domain.user.User;
import nl.fontys.lms.persistence.CourseRepository;
import nl.fontys.lms.persistence.EnrollmentRepository;
import nl.fontys.lms.persistence.entity.CourseEntity;
import nl.fontys.lms.persistence.entity.EnrollmentEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

@Service
@AllArgsConstructor
public class CreateEnrollmentUseCaseImpl implements CreateEnrollmentUseCase {
    private final EnrollmentRepository enrollmentRepository;
    private final CourseRepository courseRepository;


    @Override
    public CreateEnrollmentResponse createEnrollment(CreateEnrollmentRequest request) {
        boolean isStudentEnrolled = isStudentAlreadyEnrolled(request);
        if (isStudentEnrolled) {
            return CreateEnrollmentResponse.builder()
                    .errorMessages(new ArrayList<>(Collections.singletonList("Student is already enrolled in the course")))
                    .build();
        }

        boolean isAtFullCapacity = isCourseAtFullCapacity(request);
        if (isAtFullCapacity) {
            return CreateEnrollmentResponse.builder()
                    .errorMessages(new ArrayList<>(Collections.singletonList("Course is at full capacity")))
                    .build();
        }


        // Create and save the new enrollment
        EnrollmentEntity enrollmentEntity = saveNewEnrollment(request);

        if (enrollmentEntity != null && enrollmentEntity.getId() != null) {
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

        User user = new User();
        user.setId(request.getStudentId());

        EnrollmentEntity newEnrollment = EnrollmentEntity.builder()
                .course(courseEntity)
                .student(UserConverter.convertToEntity(user)) // Use the UserConverter to convert to UserEntity
                .enrollmentDate(new Date())
                .build();

        return enrollmentRepository.save(newEnrollment);
    }
    private boolean isStudentAlreadyEnrolled(CreateEnrollmentRequest request) {
        return enrollmentRepository.isStudentEnrolledInCourse(request.getStudentId(), request.getCourseId());
    }

    private boolean isCourseAtFullCapacity(CreateEnrollmentRequest request) {
        int currentEnrollmentCount = enrollmentRepository.getEnrollmentCountForCourse(request.getCourseId());
        int courseCapacity = courseRepository.getCourseCapacity(request.getCourseId());
        return currentEnrollmentCount >= courseCapacity;
    }

}
