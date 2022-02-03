package se.kth.recruitmentapp.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;


@Entity
@Data
@Table(name = "person")
@Component
public class Person implements PersonDTO, UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private int id;
    private String name;
    private String surname;
    private String pnr;
    private String email;
    private String password;
    private String username;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "role_id")
    private Role role;

    @Override
    public int getUserId() {
        return 0;
    }

    public Person(String name, String surname, String pnr, String email, String password, String username, Role role) {
        this.name = name;
        this.surname = surname;
        this.pnr = pnr;
        this.email = email;
        this.password = password;
        this.username = username;
        this.role = role;
    }

    public Person(){}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority(getRole().getName()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}