package web;

import java.util.Collection;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {

	Collection<Role> findAll();

	Role findByRole(@Param("role_id") String role_id);


}
