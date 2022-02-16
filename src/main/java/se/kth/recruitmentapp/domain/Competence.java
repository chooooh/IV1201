package se.kth.recruitmentapp.domain;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 * Domain model representing competence of an applicant
 */
@Entity
@Data
@Table(name = "competence")
@Component
public class Competence {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "competence_id")
    private int id;
    private String name;

    public Competence() {
    }

    public Competence(int id, String name){
        this.id = id;
        this.name = name;
    }

    //@OneToMany(mappedBy = "competence")
    //private List<Profile> profiles;
}
