package se.kth.recruitmentapp.presentation.controller;

import lombok.Data;

import org.springframework.security.crypto.password.PasswordEncoder;
import se.kth.recruitmentapp.domain.Person;
import se.kth.recruitmentapp.domain.Role;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
    @Size(min = 13, max = 13, message = "{create-acct.applicant-pnr.length}")
    private String pnr;
    @NotNull
    @NotBlank(message = "{create-acct.applicant-email.missing}")
    @Size(min = 6, max = 30, message = "{create-acct.applicant-email.length}")
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

    public Person toPerson(PasswordEncoder passwordEncoder) {
        Role role = new Role();
        role.setId(2);
        role.setName("applicant");

        Person person = new Person(firstname, surname, pnr, email, passwordEncoder.encode(password), username, role);
        return person;
    }

}
