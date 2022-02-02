package se.kth.recruitmentapp.repository;

import org.springframework.data.repository.CrudRepository;
import se.kth.recruitmentapp.domain.Availability;

public interface AvailabilityRepository extends CrudRepository<Availability, Integer> {
}
