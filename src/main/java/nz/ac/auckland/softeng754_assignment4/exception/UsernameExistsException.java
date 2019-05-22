package nz.ac.auckland.softeng754_assignment4.exception;

public class UsernameExistsException extends Exception {
    public UsernameExistsException(String errorMessage) {
        super(errorMessage);
    }
}
