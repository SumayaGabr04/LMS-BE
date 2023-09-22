package nl.fontys.lms.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import nl.fontys.lms.business.user.CreateUserUseCase;
import nl.fontys.lms.business.user.DeleteUserUseCase;
import nl.fontys.lms.business.user.GetUserUseCase;
import nl.fontys.lms.business.user.UpdateUserUseCase;
import nl.fontys.lms.domain.user.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@NoArgsConstructor(force = true)
public abstract class UsersController {
    private final GetUserUseCase getUserUseCase;
    private final CreateUserUseCase createUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;

    @GetMapping("/user/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable("id") final long id){
        final Optional<User> userOptional = getUserUseCase.getUserById(id);
        if(userOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(UserResponse.builder().id(userOptional.get().getId()).build());
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable long id) {
        deleteUserUseCase.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/createUser")
    public ResponseEntity<CreateResponse> createUser(@RequestBody @Valid UserRequest request) {
        CreateResponse response = createUserUseCase.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable("id") long id,
                                           @RequestBody @Valid UpdateUserRequest request) {
        request.setId(id);
        updateUserUseCase.updateUser(request);
        return ResponseEntity.noContent().build();
    }
}