package nl.fontys.lms.business.user.student.impl;

import lombok.AllArgsConstructor;
import nl.fontys.lms.business.user.student.GetAllStudentsUseCase;
import nl.fontys.lms.business.user.teacher.impl.TeacherConverter;
import nl.fontys.lms.domain.user.student.GetAllStudentsResponse;
import nl.fontys.lms.domain.user.student.Student;
import nl.fontys.lms.domain.user.teacher.GetAllTeachersResponse;
import nl.fontys.lms.domain.user.teacher.Teacher;
import nl.fontys.lms.persistence.StudentRepository;
import nl.fontys.lms.persistence.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GetAllStudentsUseCaseImpl implements GetAllStudentsUseCase {
    private final StudentRepository studentRepository;

    @Override
    public GetAllStudentsResponse getStudents() {
        // Retrieve all teacher entities from the repository
        List<Student> students = studentRepository.findAll().stream()
                .map(StudentConverter::convert) // Convert entities to domain objects
                .collect(Collectors.toList());

        return GetAllStudentsResponse.builder().students(students).build();
    }
}
