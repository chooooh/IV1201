package se.kth.recruitmentapp.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "person_id")
    private int id;
    private String name;
    private String surname;
    private String pnr;
    private String email;
    private String password;

    //@Column(name = "role_id")
    //private int roleId;
    @OneToOne
    @JoinColumn(name = "role_id")
    private Role role;
    private String username;

    //@OneToMany(mappedBy = "person")
    //List<Profile> profiles;
}