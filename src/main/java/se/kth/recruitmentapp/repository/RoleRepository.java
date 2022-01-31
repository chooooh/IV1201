package se.kth.recruitmentapp.repository;

import org.springframework.data.repository.CrudRepository;
import se.kth.recruitmentapp.model.Role;

public interface RoleRepository extends CrudRepository<Role, Integer> {
}
