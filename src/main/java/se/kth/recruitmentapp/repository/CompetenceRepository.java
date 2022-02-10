package se.kth.recruitmentapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import se.kth.recruitmentapp.domain.Competence;

import java.util.List;

/**
 * Contains database access methods regarding competence
 */
@Repository
//@Transactional(propagation = Propagation.MANDATORY)
public interface CompetenceRepository extends CrudRepository<Competence, Integer> {
    List<Competence> findAll();
}
