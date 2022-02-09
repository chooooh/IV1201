package se.kth.recruitmentapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.kth.recruitmentapp.domain.Competence;

/**
 * Contains database access methods regarding competence
 */
@Repository
public interface CompetenceRepository extends CrudRepository<Competence, Integer> {
}
