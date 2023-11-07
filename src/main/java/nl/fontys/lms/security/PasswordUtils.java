package nl.fontys.lms.security;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
public class PasswordUtils {
    private static final int STRENGTH = 12;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(STRENGTH);

    public static String hashPassword(String plainPassword, String salt) {
        String saltedPassword = salt + plainPassword;
        return passwordEncoder.encode(saltedPassword);
    }

    public static boolean verifyPassword(String plainPassword, String hashedPassword) {
        return passwordEncoder.matches(plainPassword, hashedPassword);
    }
}
