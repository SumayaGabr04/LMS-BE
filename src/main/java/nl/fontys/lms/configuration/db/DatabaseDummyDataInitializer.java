//package nl.fontys.lms.configuration.db;
//
//import nl.fontys.lms.business.user.CreateUserUseCase;
//import nl.fontys.lms.business.user.UserRole;
//import nl.fontys.lms.domain.user.CreateUserRequest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//@Component
//public class DatabaseDummyDataInitializer implements CommandLineRunner {
//    private final CreateUserUseCase createUserUseCase;
//
//    @Autowired
//    public DatabaseDummyDataInitializer(CreateUserUseCase createUserUseCase) {
//        this.createUserUseCase = createUserUseCase;
//    }
//
//    @Override
//    public void run(String... args) {
//        // Create dummy users on application startup
//
//        // Dummy student
//        CreateUserRequest studentRequest = CreateUserRequest.builder()
//                .firstName("Student")
//                .lastName("One")
//                .email("student@example.com")
//                .password("password123")
//                .role(UserRole.STUDENT)
//                .build();
//        createUserUseCase.createUser(studentRequest);
//
//        // Dummy teacher
//        CreateUserRequest teacherRequest = CreateUserRequest.builder()
//                .firstName("Teacher")
//                .lastName("One")
//                .email("teacher@example.com")
//                .password("password123")
//                .role(UserRole.TEACHER)
//                .build();
//        createUserUseCase.createUser(teacherRequest);
//
//        // Dummy admin
//        CreateUserRequest adminRequest = CreateUserRequest.builder()
//                .firstName("Admin")
//                .lastName("One")
//                .email("admin@example.com")
//                .password("password123")
//                .role(UserRole.ADMIN)
//                .build();
//        createUserUseCase.createUser(adminRequest);
//    }
//}
