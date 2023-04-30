package io.exception;

public class FileSaveFailureException extends RuntimeException {
    public FileSaveFailureException(String message, Throwable cause) {
        super(message, cause);
    }
}
