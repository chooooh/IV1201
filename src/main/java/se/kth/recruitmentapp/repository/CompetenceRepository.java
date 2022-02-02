package se.kth.recruitmentapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.kth.recruitmentapp.model.Competence;

@Repository
public interface CompetenceRepository extends CrudRepository<Competence, Integer> {
}
