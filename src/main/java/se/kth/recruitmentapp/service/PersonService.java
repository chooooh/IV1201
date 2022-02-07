package se.kth.recruitmentapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.kth.recruitmentapp.domain.PersonDTO;
import se.kth.recruitmentapp.repository.PersonRepository;

import javax.transaction.Transactional;

@Transactional(rollbackOn = Exception.class)
@Service
public class PersonService {
    @Autowired
    private PersonRepository repository;
    /**
     * Searches for an already registered account by the specified username.
     * @param username The username of the searched account.
     * @return The person with the specified username, null if no such person was found.
     */
    public PersonDTO findAccountByUsername(String username){
        return repository.findByUsername(username);
    }
}
