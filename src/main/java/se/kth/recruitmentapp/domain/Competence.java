package se.kth.recruitmentapp.domain;


import lombok.Data;

import javax.persistence.*;

/**
 * Domain model representing competence of an applicant
 */
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
