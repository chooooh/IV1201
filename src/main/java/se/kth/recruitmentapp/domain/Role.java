package se.kth.recruitmentapp.domain;

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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private int id;
    private String name;
}
