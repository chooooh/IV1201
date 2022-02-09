package se.kth.recruitmentapp.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Domain model representing availability of an applicant
 */
@Data
@Entity
@Table(name = "availability")
public class Availability {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "availability_id")
    private int id;
    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;
    @Column(name = "from_date")
    private Date fromDate;
    @Column(name = "to_date")
    private Date toDate;
}
