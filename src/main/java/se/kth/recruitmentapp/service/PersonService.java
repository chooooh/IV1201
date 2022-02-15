package se.kth.recruitmentapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import se.kth.recruitmentapp.domain.Person;
import se.kth.recruitmentapp.domain.Role;
import se.kth.recruitmentapp.repository.PersonRepository;
import se.kth.recruitmentapp.repository.RoleRepository;

/**
 * This is the person service class, which defines user related methods
 * that will be used by the controller classes.
 *
 * This service class is transactional, methods commits or rollbacks when returned. A new transaction is started
 * regardless of any existing transaction.
 */
@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
@Service
public class PersonService {
    public enum UserRole {
        RECRUITER,
        APPLICANT
    }
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private RoleRepository roleRepository;

    /**
     * Searches for an already registered account by the specified username.
     * @param username The username of the searched account.
     * @return The person with the specified username, null if no such person was found.
     */
    public Person findAccountByUsername(String username){
        return personRepository.findByUsername(username);
    }

    /**
     * Adds the specified person to the database.
     * @param person The person to be added to the database
     */
    public void save(Person person) {
        personRepository.save(person);
    }

    /**
     * Retrieves the specified role.
     * @param role the specified UserRole.
     * @return the specified role
     */
    public Role getRole(UserRole role) {
        switch (role) {
            case RECRUITER: return roleRepository.findById(Role.RECRUITER).get();
            case APPLICANT: return roleRepository.findById(Role.APPLICANT).get();
        }
        // throw error?
        return null;
    }
}

