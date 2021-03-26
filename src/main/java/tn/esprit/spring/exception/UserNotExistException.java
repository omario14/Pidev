package tn.esprit.spring.exception;

public class UserNotExistException extends IllegalArgumentException {
    public UserNotExistException(String msg) {
        super(msg);
    }
}
