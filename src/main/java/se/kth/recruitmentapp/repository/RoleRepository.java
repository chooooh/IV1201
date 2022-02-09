package se.kth.recruitmentapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.kth.recruitmentapp.domain.Role;

/**
 * Contains database access methods regarding roles
 */
@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
}
