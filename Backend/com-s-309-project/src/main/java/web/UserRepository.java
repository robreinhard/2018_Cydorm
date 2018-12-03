package web;

import java.util.Collection;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

/**
 * The Interface UserRepository.
 */
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

	/* (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	Collection<User> findAll();

	/**
	 * Find by net ID.
	 *
	 * @param netID the net ID
	 * @return the user
	 */
	User findByNetID(@Param("netID") String netID);

	/* (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	User save(User user);

}
