package se.kth.recruitmentapp.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import se.kth.recruitmentapp.domain.Person;
import se.kth.recruitmentapp.repository.PersonRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This class is required by Spring Security. It implements the required interface and provides an implementation for
 * fetching users as required.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = personRepository.findByUsername(username);

        if (person != null) {
            return person;
        }
        throw new UsernameNotFoundException("User '" + username + "' not found");
    }
}
