package se.kth.recruitmentapp.presentation.controller;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CreateAccountForm {
    @NotBlank(message = "{create-acct.applicant-firstname.missing}")
    @Size(min = 2, max = 30, message = "{create-acct.applicant-firstname.length}")
    private String name;
    @NotBlank(message = "{create-acct.applicant-surname.missing}")
    @Size(min = 2, max = 30, message = "{create-acct.applicant-surname.length}")
    private String surname;
    @NotBlank(message = "{create-acct.applicant-pnr.missing}")
    @Size(min = 13, max = 13, message = "{create-acct.applicant-pnr.length}")
    private String pnr;
    @NotBlank(message = "{create-acct.applicant-email.missing}")
    @Size(min = 6, max = 30, message = "{create-acct.applicant-email.length}")
    private String email;
    @NotBlank(message = "{create-acct.applicant-username.missing}")
    @Size(min = 5, max = 30, message = "{create-acct.applicant-username.length}")
    private String username;
    @NotBlank(message = "{create-acct.applicant-password.missing}")
    @Size(min = 6, max = 18, message = "{create-acct.applicant-password.length}")
    private String password;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPnr() {
        return pnr;
    }

    public void setPnr(String pnr) {
        this.pnr = pnr;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
