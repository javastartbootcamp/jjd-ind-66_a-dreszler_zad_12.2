package io.exception;

public class FileReadFailureException extends RuntimeException {
    public FileReadFailureException(String message, Throwable cause) {
        super(message, cause);
    }
}
