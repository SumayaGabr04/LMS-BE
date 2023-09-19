package nl.fontys.lms.business.user.teacher.impl;

import lombok.AllArgsConstructor;
import nl.fontys.lms.business.user.teacher.GetAllTeachersUseCase;
import nl.fontys.lms.domain.course.GetAllCoursesResponse;
import nl.fontys.lms.domain.user.teacher.GetAllTeachersResponse;
import nl.fontys.lms.domain.user.teacher.Teacher;
import nl.fontys.lms.persistence.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GetAllTeachersUseCaseImpl implements GetAllTeachersUseCase {
    private final TeacherRepository teacherRepository;

    @Override
    public GetAllTeachersResponse getTeachers() {
        // Retrieve all teacher entities from the repository
        List<Teacher> teachers = teacherRepository.findAll().stream()
                .map(TeacherConverter::convert) // Convert entities to domain objects
                .collect(Collectors.toList());

        return GetAllTeachersResponse.builder().teachers(teachers).build();
    }

}
