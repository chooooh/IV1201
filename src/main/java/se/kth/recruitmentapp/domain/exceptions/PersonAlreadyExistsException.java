package se.kth.recruitmentapp.domain.exceptions;

import org.springframework.validation.BindingResult;

/**
 * Thrown when person already exists.
 */
public class PersonAlreadyExistsException extends Exception {

    /**
     * Constructs a new instance with the message.
     * @param message Message describing the exception
     */
    public PersonAlreadyExistsException(String message) {
        super(message);
    }
}
