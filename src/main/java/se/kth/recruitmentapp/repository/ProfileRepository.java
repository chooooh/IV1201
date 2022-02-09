package se.kth.recruitmentapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import se.kth.recruitmentapp.domain.Person;
import se.kth.recruitmentapp.domain.Profile;

import java.util.List;

/**
 * Contains database access methods regarding profiles
 */
public interface ProfileRepository extends JpaRepository<Profile, Integer> {
    List<Profile> findProfileByPerson(Person person);
}

