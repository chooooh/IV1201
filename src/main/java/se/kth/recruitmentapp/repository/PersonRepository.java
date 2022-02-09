package se.kth.recruitmentapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import se.kth.recruitmentapp.domain.Person;


/**
 * Contains database access methods regarding users
 *
 * Transactional annotation is
 */
@Repository
//@Transactional(propagation = Propagation.MANDATORY)
public interface PersonRepository extends CrudRepository<Person, Integer> {
    Person findByUsername(String username);
}