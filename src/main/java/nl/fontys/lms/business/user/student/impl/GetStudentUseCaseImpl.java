package nl.fontys.lms.business.user.student.impl;

import lombok.AllArgsConstructor;
import nl.fontys.lms.business.course.GetEnrolledCoursesUseCase;
import nl.fontys.lms.business.exception.UserNotFoundException;
import nl.fontys.lms.business.user.student.GetStudentUseCase;
import nl.fontys.lms.domain.course.GetAllEnrolledCoursesRequest;
import nl.fontys.lms.domain.course.GetAllEnrolledCoursesResponse;
import nl.fontys.lms.domain.user.student.Student;
import nl.fontys.lms.persistence.StudentRepository;
import nl.fontys.lms.persistence.entity.StudentEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@AllArgsConstructor
public class GetStudentUseCaseImpl implements GetStudentUseCase {
    private final StudentRepository studentRepository;
    private final GetEnrolledCoursesUseCase getEnrolledCoursesUseCase;

    @Override
    public Optional<Student> getStudent(Long studentId) {
        StudentEntity studentEntity = studentRepository.findById(studentId);
        if (studentEntity == null) {
            throw new UserNotFoundException();
        }

        // Use the GetEnrolledCoursesUseCase to fetch the list of courses associated with the student
        GetAllEnrolledCoursesRequest request = GetAllEnrolledCoursesRequest.builder()
                .userId(studentId)
                .build();
        GetAllEnrolledCoursesResponse enrolledCoursesResponse = getEnrolledCoursesUseCase.getCourses(request);

        Student student = Student.builder()
                .id(studentEntity.getStudentId())
                .firstName(studentEntity.getFirstName())
                .lastName(studentEntity.getLastName())
                .email(studentEntity.getEmail())
                .password(studentEntity.getPassword())
                .coursesEnrolled(enrolledCoursesResponse.getCourses())
                .major(studentEntity.getMajor())
                .enrollmentDate(studentEntity.getEnrollmentDate())
                .build();

        return Optional.of(student);
    }
}
