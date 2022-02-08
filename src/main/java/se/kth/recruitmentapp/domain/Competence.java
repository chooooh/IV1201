package se.kth.recruitmentapp.domain;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "competence")
public class Competence {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "competence_id")
    private int id;
    private String name;

    //@OneToMany(mappedBy = "competence")
    //private List<Profile> profiles;
}
