package web;

import java.util.Collection;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

/**
 * The Interface RoleRepository.
 */
@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {

	/* (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	Collection<Role> findAll();

	/**
	 * Find by role.
	 *
	 * @param role_id the role id
	 * @return the role
	 */
	Role findByRole(@Param("role_id") String role_id);


}
