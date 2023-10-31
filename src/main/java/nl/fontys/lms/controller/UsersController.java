package nl.fontys.lms.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import nl.fontys.lms.business.user.*;
import nl.fontys.lms.domain.user.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UsersController {
    private final UserService userService;
    @PostMapping
    public ResponseEntity<CreateResponse> createUser(@RequestBody @Valid CreateUserRequest request) {
        CreateResponse response = userService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{role}/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable("role") String role, @PathVariable("id") long id) {
        Optional<UserResponse> userResponseOptional = userService.getUserById(id, role);

        if (userResponseOptional.isPresent()) {
            // The Optional contains a value, so unwrap it and return the UserResponse
            return ResponseEntity.ok(userResponseOptional.get());
        } else {
            // Handle the case where the user is not found or the role is invalid
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("/{role}/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable("role") String role, @PathVariable("id") long id,
                                           @RequestBody @Valid UpdateUserRequest request) {
        request.setRole(role);
        userService.updateUser(request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{role}/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("role") String role, @PathVariable("id") long id) {
        userService.deleteUser(id, role);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/all")
    public ResponseEntity<ArrayList<User>> getAllUsers() {
        ArrayList<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

}
