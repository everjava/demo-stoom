package br.com.stoom.demo.exception;

public class CoordinatesException extends RuntimeException{

    public CoordinatesException() {
    }

    public CoordinatesException(String message) {
        super(message);
    }

    public CoordinatesException(String message, Throwable cause) {
        super(message, cause);
    }

    public CoordinatesException(Throwable cause) {
        super(cause);
    }

    public CoordinatesException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
