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
import nl.fontys.lms.domain.user.admin.CreateAdminRequest;
import nl.fontys.lms.domain.user.student.CreateStudentRequest;
import nl.fontys.lms.domain.user.teacher.CreateTeacherRequest;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Map;
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


        if (request.getRole() == UserRole.STUDENT) {
            System.out.println("Role: " + request.getRole());
            CreateStudentRequest studentRequest = CreateStudentRequest.fromUserRequestAndMajor(request, ((CreateStudentRequest) request).getMajor());
            return createStudent(studentRequest);

        } else if (request.getRole() == UserRole.TEACHER) {
            CreateTeacherRequest teacherRequest = CreateTeacherRequest.fromUserRequestAndDepartment(request, ((CreateTeacherRequest) request).getDepartment());
            return createTeacher(teacherRequest);

        } else if (request.getRole() == UserRole.ADMIN) {
            CreateAdminRequest adminRequest = CreateAdminRequest.fromUserRequestAndDepartment(request, ((CreateAdminRequest) request).getDepartment());
            return createAdmin(adminRequest);

        } else {
            // Handle invalid or unknown roles
            throw new InvalidRoleException();
        }
    }

    public CreateResponse createStudent(CreateStudentRequest request) {
        return createStudentUseCase.createStudent(request);
    }

    public CreateResponse createTeacher(CreateTeacherRequest request) {
        return createTeacherUseCase.createTeacher(request);
    }

    public CreateResponse createAdmin(CreateAdminRequest request) {
        return createAdminUseCase.createAdmin(request);
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

    public CreateUserRequest mapToCreateUserRequest(Map<String, Object> requestMap) {
        String role = ((String) requestMap.get("role")).toUpperCase();
        System.out.println("Role extracted from request: " + role);

        switch (role) {
            case "STUDENT":
                return mapToCreateStudentRequest(requestMap);
            case "TEACHER":
                return mapToCreateTeacherRequest(requestMap);
            case "ADMIN":
                return mapToCreateAdminRequest(requestMap);
            default:
                throw new InvalidRoleException();
        }
    }

    private CreateStudentRequest mapToCreateStudentRequest(Map<String, Object> requestMap) {
        // Extract and map fields for CreateStudentRequest
        // For example:
        String firstName = (String) requestMap.get("firstName");
        String lastName = (String) requestMap.get("lastName");
        String email = (String) requestMap.get("email");
        String password = (String) requestMap.get("password");
        String major = (String) requestMap.get("major");

        // Create instances directly without casting
        CreateUserRequest userRequest = CreateUserRequest.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .password(password)
                .role(UserRole.STUDENT)
                .build();

        CreateStudentRequest studentRequest = CreateStudentRequest.studentBuilder()
                .user(userRequest)
                .major(major)
                .build();

        return studentRequest;
    }


    private CreateTeacherRequest mapToCreateTeacherRequest(Map<String, Object> requestMap) {
        // Implement logic to extract and map fields for CreateTeacherRequest
        // For example:
        String firstName = (String) requestMap.get("firstName");
        String lastName = (String) requestMap.get("lastName");
        String email = (String) requestMap.get("email");
        String password = (String) requestMap.get("password");
        String department = (String) requestMap.get("department");

        return CreateTeacherRequest.teacherBuilder()
                .user(CreateUserRequest.builder()
                        .firstName(firstName)
                        .lastName(lastName)
                        .email(email)
                        .password(password)
                        .role(UserRole.TEACHER)
                        .build())
                .department(department)
                .build();
    }

    private CreateAdminRequest mapToCreateAdminRequest(Map<String, Object> requestMap) {
        // Implement logic to extract and map fields for CreateAdminRequest
        // For example:
        String firstName = (String) requestMap.get("firstName");
        String lastName = (String) requestMap.get("lastName");
        String email = (String) requestMap.get("email");
        String password = (String) requestMap.get("password");
        String department = (String) requestMap.get("department");

        return CreateAdminRequest.adminBuilder()
                .user(CreateUserRequest.builder()
                        .firstName(firstName)
                        .lastName(lastName)
                        .email(email)
                        .password(password)
                        .role(UserRole.ADMIN)
                        .build())
                .department(department)
                .build();
    }
}