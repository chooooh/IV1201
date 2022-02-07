package se.kth.recruitmentapp.domain;

import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class RegistrationForm {
    private String name;
    private String surname;
    private String pnr;
    private String email;
    private String password;
    private String username;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Person toPerson(PasswordEncoder passwordEncoder) {
        Role role = new Role();
        role.setId(2);
        role.setName("applicant");

        Person person = new Person(name, surname, pnr, email, passwordEncoder.encode(password), username, role);
        return person;
    }

}
