package se.kth.recruitmentapp.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "role", schema = "public")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private int id;
    private String name;
}
