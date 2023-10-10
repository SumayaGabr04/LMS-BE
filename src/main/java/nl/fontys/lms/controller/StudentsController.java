package nl.fontys.lms.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import nl.fontys.lms.business.user.student.*;
import nl.fontys.lms.domain.user.CreateResponse;
import nl.fontys.lms.domain.user.student.*;
import nl.fontys.lms.domain.user.teacher.GetAllTeachersResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/students")
@AllArgsConstructor
public class StudentsController extends UsersController{
    private final GetStudentUseCase getStudentUseCase;
    private final CreateStudentUseCase createStudentUseCase;
    private final UpdateStudentUseCase updateStudentUseCase;
    private final DeleteStudentUseCase deleteStudentUseCase;
    private final GetAllStudentsUseCase getAllStudentsUseCase;

    @GetMapping("/student/{id}")
    public ResponseEntity<GetStudentResponse> getStudent(@PathVariable("id") final long id) {
        final Optional<Student> studentOptional = getStudentUseCase.getStudent(id);
        if (studentOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(GetStudentResponse.builder().id(studentOptional.get().getId()).build());
    }
    @GetMapping
    public ResponseEntity<GetAllStudentsResponse> getAllStudents() {
        return ResponseEntity.ok(getAllStudentsUseCase.getStudents());
    }

    @DeleteMapping("/student/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable long id) {
        deleteStudentUseCase.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/createStudent")
    public ResponseEntity<CreateResponse> createStudent(@RequestBody @Valid CreateStudentRequest request) {
        CreateResponse response = createStudentUseCase.createStudent(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/updateStudent/{id}")
    public ResponseEntity<Void> updateStudent(@PathVariable("id") long id,
                                              @RequestBody @Valid UpdateStudentRequest request) {
        request.setId(id);
        updateStudentUseCase.updateStudent(request);
        return ResponseEntity.noContent().build();
    }
    //method for getting the courses they are enrolled into
}
