package se.kth.recruitmentapp.repository;

import org.springframework.data.repository.CrudRepository;
import se.kth.recruitmentapp.domain.Person;
import se.kth.recruitmentapp.domain.Profile;

import java.util.List;

public interface ProfileRepository extends CrudRepository<Profile, Integer> {
    List<Profile> findProfileByPerson(Person person);
}

