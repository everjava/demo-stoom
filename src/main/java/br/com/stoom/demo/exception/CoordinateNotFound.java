package br.com.stoom.demo.exception;

public class CoordinateNotFound extends RuntimeException {

    public CoordinateNotFound() {
    }

    public CoordinateNotFound(String message) {
        super(message);
    }

    public CoordinateNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public CoordinateNotFound(Throwable cause) {
        super(cause);
    }

    public CoordinateNotFound(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
