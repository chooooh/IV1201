package se.kth.recruitmentapp.repository;

import org.springframework.data.repository.CrudRepository;
import se.kth.recruitmentapp.domain.Person;

public interface PersonRepository extends CrudRepository<Person, Integer> {
    Person findByUsername(String username);
}