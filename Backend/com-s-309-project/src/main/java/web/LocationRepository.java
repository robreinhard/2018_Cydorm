package web;

import java.util.Collection;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

/**
 * The Interface LocationRepository.
 */
@Repository
public interface LocationRepository extends CrudRepository<Location, Integer> {

	/* (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	Collection<Location> findAll();
	
	/**
	 * Find by location.
	 *
	 * @param location the location
	 * @return the location
	 */
	Location findByLocation(@Param("location") String location);
	
	/* (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	Location save(Location location);
}
