package nl.fontys.lms.controller;

import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import nl.fontys.lms.business.enrollment.CreateEnrollmentUseCase;
import nl.fontys.lms.business.enrollment.DeleteEnrollmentUseCase;
import nl.fontys.lms.business.enrollment.GetEnrollmentsForCourseUseCase;
import nl.fontys.lms.business.enrollment.GetEnrollmentsForStudentUseCase;
import nl.fontys.lms.domain.enrollment.CreateEnrollmentRequest;
import nl.fontys.lms.domain.enrollment.CreateEnrollmentResponse;
import nl.fontys.lms.domain.enrollment.GetEnrollmentsForCourseResponse;
import nl.fontys.lms.domain.enrollment.GetEnrollmentsForStudentResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/enrollments")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
@RolesAllowed("STUDENT")
public class EnrollmentsController {
    private final CreateEnrollmentUseCase createEnrollmentUseCase;
    private final GetEnrollmentsForCourseUseCase getEnrollmentsForCourseUseCase;
    private final GetEnrollmentsForStudentUseCase getEnrollmentsForStudentUseCase;
    private final DeleteEnrollmentUseCase deleteEnrollmentUseCase;

    @PostMapping()
    public ResponseEntity<CreateEnrollmentResponse> createEnrollment(@RequestBody @Valid CreateEnrollmentRequest request) {
        CreateEnrollmentResponse response = createEnrollmentUseCase.createEnrollment(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("course/{courseId}")
    public ResponseEntity<GetEnrollmentsForCourseResponse> getEnrollmentsForCourse(@PathVariable("courseId") Long courseId) {
        GetEnrollmentsForCourseResponse response = getEnrollmentsForCourseUseCase.getEnrollmentsForCourse(courseId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("student/{studentId}")
    public ResponseEntity<GetEnrollmentsForStudentResponse> getEnrollmentsForStudent(@PathVariable("studentId") Long studentId) {
        GetEnrollmentsForStudentResponse response = getEnrollmentsForStudentUseCase.getEnrollmentsForStudent(studentId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{enrollmentId}")
    public ResponseEntity<Void> deleteEnrollment(@PathVariable("enrollmentId") Long enrollmentId) {
        deleteEnrollmentUseCase.deleteEnrollment(enrollmentId);
        return ResponseEntity.noContent().build();
    }
}
