package se.kth.recruitmentapp.domain.exceptions;

/**
 * Thrown when Role is not found
 */
public class RoleNotFoundException extends Exception {
    public static final String NOT_FOUND = "Role not found";
    /**
     * Constructs a new instance with the message.
     * @param message Message describing the exception
     */
    public RoleNotFoundException(String message) {
        super(message);
    }
}
