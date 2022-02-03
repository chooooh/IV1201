package se.kth.recruitmentapp.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Defines all operations that can be performed on an {@link Person}
 */
public interface PersonDTO {
    /**
     *
     * @return the Username of the User.
     */
    String getUsername();

    /**
     *
     * @return The ID of this user. Unique for all users.
     */
    int getUserId();

    /**
     *
     * @return the Password of the User.
     */
    String getPassword();

}
