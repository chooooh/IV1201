package se.kth.recruitmentapp.presentation.forms;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import se.kth.recruitmentapp.domain.models.Person;
import se.kth.recruitmentapp.domain.models.Role;
import javax.validation.constraints.*;

/**
 * A form bean for account creation.
 * It includes validation control. The appropriate validations checks are implemented here.
 */
@Data
public class CreateAccountForm {
    @NotNull
    @NotBlank(message = "{create-acct.applicant-firstname.missing}")
    @Size(min = 2, max = 30, message = "{create-acct.applicant-firstname.length}")
    private String firstname;
    @NotNull
    @NotBlank(message = "{create-acct.applicant-surname.missing}")
    @Size(min = 2, max = 30, message = "{create-acct.applicant-surname.length}")
    private String surname;
    @NotNull
    @NotBlank(message = "{create-acct.applicant-pnr.missing}")
    @Pattern(regexp = "^\\d{6}(?:\\d{2})?[-\\s]?\\d{4}$", message = "{create-acct.applicant-pnr.format}")
    private String pnr;
    @NotNull
    @NotBlank(message = "{create-acct.applicant-email.missing}")
    @Email(message = "{create-acct.applicant-email.format}")
    private String email;
    @NotNull
    @NotBlank(message = "{create-acct.applicant-username.missing}")
    @Size(min = 5, max = 30, message = "{create-acct.applicant-username.length}")
    private String username;
    @NotNull
    @NotBlank(message = "{create-acct.applicant-password.missing}")
    @Size(min = 6, max = 18, message = "{create-acct.applicant-password.length}")
    private String password;
    @NotNull
    @NotBlank(message = "{create-acct.applicant-password.missing}")
    @Size(min = 6, max = 18, message = "{create-acct.applicant-password.length}")
    private String confirmPassword;

    /**
     * Converts this class to a Person object.
     * @param passwordEncoder the required password encoder.
     * @param role the required Role.
     * @return a new Person object.
     */
    public Person toPerson(PasswordEncoder passwordEncoder, Role role) {
        Person person = new Person(firstname, surname, pnr, email, passwordEncoder.encode(password), username, role);
        return person;
    }

}
