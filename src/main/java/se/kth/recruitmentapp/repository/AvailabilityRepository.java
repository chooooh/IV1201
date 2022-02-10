package se.kth.recruitmentapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.kth.recruitmentapp.domain.Availability;

/**
 * Contains database access methods regarding availability
 */
@Repository
public interface AvailabilityRepository extends CrudRepository<Availability, Integer> {
    
}
