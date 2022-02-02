package se.kth.recruitmentapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.kth.recruitmentapp.model.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
}
