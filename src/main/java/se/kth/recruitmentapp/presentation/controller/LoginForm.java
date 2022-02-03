package se.kth.recruitmentapp.presentation.controller;


import javax.validation.constraints.NotBlank;

/**
 * A form bean for logging in a user
 */
public class LoginForm {
    @NotBlank(message = "{login-user.username.missing}")
    private String username;

    @NotBlank(message = "{login-user.password.missing}")
    private String password;

    /**
     *
     * @return The username given in the form
     */
    public String getUsername(){
        return username;
    }

    /**
     *
     * @return The password given in the form
     */
    public String getPassword(){
        return password;
    }

    /**
     * Sets the username to that which was provided by the user who wishes to log in.
     * @param username the username provided by the user who wishes to log in
     */
    public void setUsername(String username){
        this.username = username;
    }

    /**
     * Sets the password to that which was provided by the user who wishes to log in.
     * @param password the password provided by the user who wishes to log in
     */
    public void setPassword(String password){
        this.password = password;
    }

}
