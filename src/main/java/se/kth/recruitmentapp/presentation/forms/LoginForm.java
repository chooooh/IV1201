package se.kth.recruitmentapp.presentation.forms;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * A form bean for logging in a user.
 * This form contains all properties needed for logging in a user.
 * Methods in this from can be invoked by controllers to set and get the data inserted by the user.
 * * It includes validation control. The appropriate validations checks are implemented here.
 */
@Data
public class LoginForm {
    @NotNull
    @NotBlank(message = "{login-user.username.missing}")
    private String username;
    @NotNull
    @NotBlank(message = "{login-user.password.missing}")
    private String password;
}
