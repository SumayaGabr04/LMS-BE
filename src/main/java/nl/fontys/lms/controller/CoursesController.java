package nl.fontys.lms.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import nl.fontys.lms.business.course.*;
import nl.fontys.lms.domain.course.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/courses")
@AllArgsConstructor
public class CoursesController {
    private final GetCourseUseCase getCourseUseCase;
    private final GetEnrolledCoursesUseCase getEnrolledCoursesUseCase;
    private final GetAllCoursesUseCase getAllCoursesUseCase;
    private final DeleteCourseUseCase deleteCourseUseCase;
    private final CreateCourseUseCase createCourseUseCase;
    private final UpdateCourseUseCase updateCourseUseCase;

    @GetMapping("{id}")
    public ResponseEntity<Course> getCourse(@PathVariable("id") final long id){
        final Optional<Course> courseOptional = getCourseUseCase.getCourse(id);
        if(courseOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(courseOptional.get());
    }
    @GetMapping("{userId}")
    public ResponseEntity<GetAllEnrolledCoursesResponse> getAllEnrolledCourses(@RequestParam(value = "userId", required = false) int userId){
        GetAllEnrolledCoursesRequest request = GetAllEnrolledCoursesRequest.builder().userId(userId).build();
        GetAllEnrolledCoursesResponse response = getEnrolledCoursesUseCase.getCourses(request);
        return ResponseEntity.ok(response);
    }
    @GetMapping
    public ResponseEntity<GetAllCoursesResponse> getAllCourses() {
        return ResponseEntity.ok(getAllCoursesUseCase.getCourses());
    }

    @DeleteMapping("{courseId}")
    public ResponseEntity<Void> deleteCourse(@PathVariable int courseId) {
        deleteCourseUseCase.deleteCourse(courseId);
        return ResponseEntity.noContent().build();
    }
    @PostMapping()
    public ResponseEntity<CreateCourseResponse> createCourse(@RequestBody @Valid CreateCourseRequest request) {
        CreateCourseResponse response = createCourseUseCase.createCourse(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @PutMapping("{id}")
    public ResponseEntity<Void> updateCourse(@PathVariable("id") long id,
                                            @RequestBody @Valid UpdateCourseRequest request) {
        request.setId(id);
        updateCourseUseCase.UpdateCourse(request);
        return ResponseEntity.noContent().build();
    }
}
