package nl.fontys.lms.security;

import java.security.SecureRandom;
import java.util.Base64;

public class SaltUtils {
    private static final SecureRandom secureRandom = new SecureRandom();

    public static String generateSalt() {
        byte[] salt = new byte[16];
        secureRandom.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }
}