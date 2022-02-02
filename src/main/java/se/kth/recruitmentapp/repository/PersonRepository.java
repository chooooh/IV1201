package se.kth.recruitmentapp.repository;

import org.springframework.data.repository.CrudRepository;
import se.kth.recruitmentapp.model.Person;

public interface PersonRepository extends CrudRepository<Person, Integer> {
}