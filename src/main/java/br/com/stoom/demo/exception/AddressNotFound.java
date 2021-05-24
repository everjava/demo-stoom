package br.com.stoom.demo.exception;

public class AddressNotFound extends  RuntimeException  {


    public AddressNotFound() {
    }

    public AddressNotFound(String message) {
        super(message);
    }

    public AddressNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public AddressNotFound(Throwable cause) {
        super(cause);
    }

    public AddressNotFound(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
