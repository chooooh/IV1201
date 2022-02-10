package se.kth.recruitmentapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import se.kth.recruitmentapp.domain.Person;
import se.kth.recruitmentapp.repository.PersonRepository;


@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
@Service
public class PersonService {
    @Autowired
    private PersonRepository repository;
    /**
     * Searches for an already registered account by the specified username.
     * @param username The username of the searched account.
     * @return The person with the specified username, null if no such person was found.
     */
    public Person findAccountByUsername(String username){
        return repository.findByUsername(username);
    }

    /**
     * Adds the specified person to the database.
     * @param person The person to be added to the database
     */
    public void save(Person person) {
        repository.save(person);
    }
}
