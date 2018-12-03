package web;

import java.util.Collection;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

/**
 * The Interface SublocationRepository.
 */
@Repository
public interface SublocationRepository extends CrudRepository<Sublocation, Integer> {

	/* (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	Collection<Sublocation> findAll();
	
	/**
	 * Find by sublocation.
	 *
	 * @param sublocation the sublocation
	 * @return the sublocation
	 */
	Sublocation findBySublocation(@Param("sublocation") String sublocation);
	
	/* (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	Sublocation save(Sublocation sublocation);
}
