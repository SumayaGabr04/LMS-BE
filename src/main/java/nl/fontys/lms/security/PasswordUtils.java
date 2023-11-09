package nl.fontys.lms.security;

import java.security.MessageDigest;
import java.util.Base64;

public class PasswordUtils {
    public static String hashPassword(String plainPassword, String salt) {
        String saltedPassword = salt + plainPassword;
        String hashedPassword = hashWithSalt(saltedPassword, salt);
        return hashedPassword;
    }

    public static boolean verifyPassword(String plainPassword, String hashedPassword, String salt) {
        String saltedPassword = salt + plainPassword;
        String rehashedPassword = hashWithSalt(saltedPassword, salt);
        return rehashedPassword.equals(hashedPassword);
    }

    private static String hashWithSalt(String value, String salt) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(salt.getBytes());
            byte[] hashedBytes = messageDigest.digest(value.getBytes());
            return Base64.getEncoder().encodeToString(hashedBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

//    private static final int STRENGTH = 12;
//
//    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(STRENGTH);
//
//    public static String hashPassword(String plainPassword, String salt) {
//        String saltedPassword = salt + plainPassword;
//        return passwordEncoder.encode(saltedPassword);
//    }
//
//    public static boolean verifyPassword(String plainPassword, String hashedPassword) {
//        return passwordEncoder.matches(plainPassword, hashedPassword);
//    }
}
