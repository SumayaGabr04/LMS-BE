package nl.fontys.lms.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import nl.fontys.lms.business.user.*;
import nl.fontys.lms.domain.user.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UsersController {
    private final CreateUserUseCase createUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final GetAllUsersUseCase getUsersUseCase;
    private final GetUserUseCase getUserUseCase;

    @PostMapping
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody CreateUserRequest request) {
        CreateUserResponse response = createUserUseCase.createUser(request);
        CreateUserResponse createResponse = CreateUserResponse.builder().id(response.getId()).build();
        return new ResponseEntity<>(createResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<GetUserResponse> getUser(@PathVariable Long userId) {
        GetUserRequest request = GetUserRequest.builder().id(userId).build();
        GetUserResponse response = getUserUseCase.getUserById(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<GetAllUsersResponse> getUsers() {
        ArrayList<User> users = getUsersUseCase.getAllUsers().getUsers();
        GetAllUsersResponse response = GetAllUsersResponse.builder().users(users).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Void> updateUser(@PathVariable Long userId, @RequestBody UpdateUserRequest request) {
        request.setId(userId);
        updateUserUseCase.updateUser(request);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
