package nl.fontys.lms.business.material.impl;

public class MaterialUploadException extends RuntimeException{
    public MaterialUploadException(String message) {
        super(message);
    }

    public MaterialUploadException(String message, Throwable cause) {
        super(message, cause);
    }
}
