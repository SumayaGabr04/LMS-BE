package nl.fontys.lms.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import nl.fontys.lms.business.user.teacher.*;
import nl.fontys.lms.domain.course.GetAllCoursesResponse;
import nl.fontys.lms.domain.user.CreateResponse;
import nl.fontys.lms.domain.user.teacher.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.Optional;

@RestController
@RequestMapping("/teachers")
@AllArgsConstructor
public class TeachersController {
    private final GetTeacherUseCase getTeacherUseCase;
    private final CreateTeacherUseCase createTeacherUseCase;
    private final UpdateTeacherUseCase updateTeacherUseCase;
    private final DeleteTeacherUseCase deleteTeacherUseCase;
    private final GetAllTeachersUseCase getAllTeachersUseCase;

    @GetMapping("{id}")
    public ResponseEntity<GetTeacherResponse> getTeacher(@PathVariable("id") final long id){
        final Optional<Teacher> teacherOptional = getTeacherUseCase.getTeacher(id);
        if(teacherOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(GetTeacherResponse.builder().id(teacherOptional.get().getId()).build());
    }

    @GetMapping
    public ResponseEntity<GetAllTeachersResponse> getAllTeachers() {
        return ResponseEntity.ok(getAllTeachersUseCase.getTeachers());
    }

    @DeleteMapping("{teacherId}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable long teacherId) {
        deleteTeacherUseCase.deleteTeacher(teacherId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping()
    public ResponseEntity<CreateResponse> createTeacher(@RequestBody @Valid CreateTeacherRequest request) {
        CreateResponse response = createTeacherUseCase.createTeacher(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateTeacher(@PathVariable("id") long id,
                                              @RequestBody @Valid UpdateTeacherRequest request) {
        request.setId(id);
        updateTeacherUseCase.updateTeacher(request);
        return ResponseEntity.noContent().build();
    }
}
