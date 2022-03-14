package se.kth.recruitmentapp.domain.models;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.math.BigDecimal;

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
    private int competenceNameId;
    private String name;
    private String languageCode;


    public Competence() {
    }

    public Competence(int id, String name){
        this.id = id;
        this.name = name;
    }

    //@OneToMany(mappedBy = "competence")
    //private List<Profile> profiles;
}
