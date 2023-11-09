package nl.fontys.lms.business.user;

public class UserRoleUtil {
    public static String userRoleToString(UserRole role) {
        return role.name();
    }

    public static UserRole stringToUserRole(String role) {
        return UserRole.valueOf(role);
    }
}
