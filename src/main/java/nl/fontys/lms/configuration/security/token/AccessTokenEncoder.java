package nl.fontys.lms.configuration.security.token;

public interface AccessTokenEncoder {
    String encode(AccessToken accessToken);
}
