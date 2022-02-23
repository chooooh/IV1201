package se.kth.recruitmentapp.domain.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Domain model representing the role of a person. Can be either applicant or recruiter.
 */
@Entity
@Data
@Table(name = "role", schema = "public")
@NoArgsConstructor
public class Role {
    // kan man hantera detta på annat sätt?
    public static final int RECRUITER = 1;
    public static final int APPLICANT = 2;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private int id;
    private String name;

}
