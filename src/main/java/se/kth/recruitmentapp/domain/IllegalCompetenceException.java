package se.kth.recruitmentapp.domain;

/**
 * Thrown when applicants does not follow business rules regarding
 * competence.
 */
public class IllegalCompetenceException extends Exception {
    /**
     * Constructs a new instance with the message.
     * @param msg Message describing the exception
     */
    public IllegalCompetenceException(String msg) {
        super(msg);
    }
}
