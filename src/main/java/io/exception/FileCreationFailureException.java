package io.exception;

public class FileCreationFailureException extends RuntimeException {
    public FileCreationFailureException(String message, Throwable cause) {
        super(message, cause);
    }
}
