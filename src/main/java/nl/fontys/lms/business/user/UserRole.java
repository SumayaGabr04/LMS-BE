package nl.fontys.lms.business.user;

public enum UserRole {
    STUDENT, TEACHER, ADMIN;
    public String getValue() {
        return this.name();
    }
}
