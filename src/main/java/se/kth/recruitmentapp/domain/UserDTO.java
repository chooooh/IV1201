package se.kth.recruitmentapp.domain;

/**
 * Defines all operations that can be performed on an {@link User}
 */
public interface UserDTO {
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
