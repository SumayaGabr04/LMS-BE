package nl.fontys.lms.business.user;

import lombok.AllArgsConstructor;
import nl.fontys.lms.business.exception.InvalidRoleException;
import nl.fontys.lms.business.exception.UserNotFoundException;
import nl.fontys.lms.business.user.admin.CreateAdminUseCase;
import nl.fontys.lms.business.user.admin.DeleteAdminUseCase;
import nl.fontys.lms.business.user.admin.GetAdminUseCase;
import nl.fontys.lms.business.user.admin.UpdateAdminUseCase;
import nl.fontys.lms.business.user.impl.MapToUserResponse;
import nl.fontys.lms.business.user.student.CreateStudentUseCase;
import nl.fontys.lms.business.user.student.DeleteStudentUseCase;
import nl.fontys.lms.business.user.student.GetStudentUseCase;
import nl.fontys.lms.business.user.student.UpdateStudentUseCase;
import nl.fontys.lms.business.user.teacher.CreateTeacherUseCase;
import nl.fontys.lms.business.user.teacher.DeleteTeacherUseCase;
import nl.fontys.lms.business.user.teacher.GetTeacherUseCase;
import nl.fontys.lms.business.user.teacher.UpdateTeacherUseCase;
import nl.fontys.lms.domain.user.*;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final CreateStudentUseCase createStudentUseCase;
    private final CreateTeacherUseCase createTeacherUseCase;
    private final CreateAdminUseCase createAdminUseCase;
    private final GetStudentUseCase getStudentUseCase;
    private final GetTeacherUseCase getTeacherUseCase;
    private final GetAdminUseCase getAdminUseCase;
    private final UpdateStudentUseCase updateStudentUseCase;
    private final UpdateTeacherUseCase updateTeacherUseCase;
    private final UpdateAdminUseCase updateAdminUseCase;
    private final DeleteStudentUseCase deleteStudentUseCase;
    private final DeleteTeacherUseCase deleteTeacherUseCase;
    private final DeleteAdminUseCase deleteAdminUseCase;
    private final MapToUserResponse mapToUserResponse;
    private final GetAllUsersUseCase getAllUsersUseCase;

    public CreateResponse createUser(CreateUserRequest request) {
        // Determine the role from the request and delegate to the corresponding use case
        if ("student".equalsIgnoreCase(request.getRole())) {
            return createStudentUseCase.createStudent(request);
        } else if ("teacher".equalsIgnoreCase(request.getRole())) {
            return createTeacherUseCase.createTeacher(request);
        } else if ("admin".equalsIgnoreCase(request.getRole())) {
            return createAdminUseCase.createAdmin(request);
        } else {
            // Handle invalid or unknown roles
            throw new InvalidRoleException();
        }
    }

    public Optional<UserResponse> getUserById(long userId, String role) {
        Optional<?> userOptional;

        switch (role.toLowerCase()) {
            case "student":
                userOptional = getStudentUseCase.getStudent(userId);
                break;
            case "teacher":
                userOptional = getTeacherUseCase.getTeacher(userId);
                break;
            case "admin":
                userOptional = getAdminUseCase.getAdmin(userId);
                break;
            default:
                throw new InvalidRoleException();
        }

        if (userOptional.isEmpty()) {
            throw new UserNotFoundException();
        }

        UserResponse userResponse = mapToUserResponse.mapToUserResponse(userOptional.get());

        return Optional.of(userResponse);
    }

    public void updateUser(UpdateUserRequest request) {
        String role = request.getRole();
        if ("student".equalsIgnoreCase(role)) {
            updateStudentUseCase.updateStudent(request);
        } else if ("teacher".equalsIgnoreCase(role)) {
            updateTeacherUseCase.updateTeacher(request);
        } else if ("admin".equalsIgnoreCase(role)) {
            updateAdminUseCase.updateAdmin(request);
        } else {
            // Handle invalid or unknown roles
            throw new InvalidRoleException();
        }
    }

    public void deleteUser(long userId, String role) {
        if ("student".equalsIgnoreCase(role)) {
            deleteStudentUseCase.deleteStudent(userId);
        } else if ("teacher".equalsIgnoreCase(role)) {
            deleteTeacherUseCase.deleteTeacher(userId);
        } else if ("admin".equalsIgnoreCase(role)) {
            deleteAdminUseCase.deleteAdmin(userId);
        } else {
            // Handle invalid or unknown roles
            throw new InvalidRoleException();
        }
    }
    public ArrayList<User> getAllUsers() {
        GetAllUsersResponse response = getAllUsersUseCase.getAllUsers();
        return response.getUsers();
    }
}
