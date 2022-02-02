package se.kth.recruitmentapp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USER")
public class User implements UserDTO{
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public int getUserId() {
        return 0;
    }

    @Override
    public String getPassword() {
        return null;
    }
}
