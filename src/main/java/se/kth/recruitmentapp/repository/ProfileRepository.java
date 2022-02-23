package se.kth.recruitmentapp.repository;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import se.kth.recruitmentapp.domain.models.Person;
import se.kth.recruitmentapp.domain.models.Profile;

import java.util.List;

/**
 * Contains database access methods regarding profiles
 *
 * This repository class is annotated with transactional, which is required for establishing transactions for explicitly
 * defined methods. Methods commits or rollbacks when returned. Throws an exception if there is not an active transaction.
 */
@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface ProfileRepository extends JpaRepository<Profile, Integer> {
    List<Profile> findProfileByPerson(Person person);
    PageImpl<Profile> findAll(Pageable pageable);

}

