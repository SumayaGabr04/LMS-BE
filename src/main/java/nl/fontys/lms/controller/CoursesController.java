package nl.fontys.lms.controller;

import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import nl.fontys.lms.business.course.*;
import nl.fontys.lms.domain.course.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/courses")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class CoursesController {
    private final GetCourseUseCase getCourseUseCase;
    private final GetAllCoursesUseCase getAllCoursesUseCase;
    private final DeleteCourseUseCase deleteCourseUseCase;
    private final CreateCourseUseCase createCourseUseCase;
    private final UpdateCourseUseCase updateCourseUseCase;
    private final TopCoursesUseCase topCoursesUseCase;

    @RolesAllowed({"ADMIN", "TEACHER", "STUDENT"})
    @GetMapping("{id}")
    public ResponseEntity<Course> getCourse(@PathVariable("id") final long id){
        final Optional<Course> courseOptional = getCourseUseCase.getCourse(id);
        if(courseOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(courseOptional.get());
    }
    @RolesAllowed({"ADMIN", "TEACHER", "STUDENT"})
    @GetMapping
    public ResponseEntity<GetAllCoursesResponse> getAllCourses() {
        return ResponseEntity.ok(getAllCoursesUseCase.getCourses());
    }
    @RolesAllowed("ADMIN")
    @DeleteMapping("{courseId}")
    public ResponseEntity<Void> deleteCourse(@PathVariable int courseId) {
        deleteCourseUseCase.deleteCourse(courseId);
        return ResponseEntity.noContent().build();
    }
    @RolesAllowed("ADMIN")
    @PostMapping()
    public ResponseEntity<CreateCourseResponse> createCourse(@RequestBody @Valid CreateCourseRequest request) {
        CreateCourseResponse response = createCourseUseCase.createCourse(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @RolesAllowed("ADMIN")
    @PutMapping("{id}")
    public ResponseEntity<Void> updateCourse(@PathVariable("id") long id,
                                            @RequestBody @Valid UpdateCourseRequest request) {
        request.setId(id);
        updateCourseUseCase.updateCourse(request);
        return ResponseEntity.noContent().build();
    }

    @RolesAllowed({"ADMIN", "TEACHER", "STUDENT"})
    @GetMapping("/search")
    public ResponseEntity<GetAllCoursesResponse> searchCourses(@RequestParam String searchTerm) {
        return ResponseEntity.ok(getAllCoursesUseCase.searchCourses(searchTerm));
    }

    @RolesAllowed({"ADMIN", "TEACHER", "STUDENT"})
    @GetMapping("/top3enrolled")
    public ResponseEntity<TopCoursesResponse> getTop3CoursesWithMostEnrolledStudents() {
        TopCoursesResponse topCoursesResponse = topCoursesUseCase.getTop3CoursesWithMostEnrolledStudents();
        System.out.println("Top 3 Courses Response: " + topCoursesResponse);
        return ResponseEntity.ok(topCoursesResponse);
    }
}
