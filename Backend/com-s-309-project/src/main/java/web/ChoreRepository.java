package web;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * The Interface ChoreRepository.
 */
@Repository
public interface ChoreRepository extends CrudRepository<Chore, Integer> {

	/* (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	Collection<Chore> findAll();

	/**
	 * Find by id.
	 *
	 * @param chore_id the chore id
	 * @return the chore
	 */
	Chore findById(@Param("chore_id") int chore_id);

	/* (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	Chore save(Chore chore);
	
    /* (non-Javadoc)
     * @see org.springframework.data.repository.CrudRepository#delete(java.lang.Object)
     */
    void delete(Chore deleted);
}
