package web;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * The Interface DisputeRepository.
 */
@Repository
public interface DisputeRepository extends CrudRepository<Dispute, Integer> {
	
	/* (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	Collection<Dispute> findAll();

	/**
	 * Find by id.
	 *
	 * @param dispute_id the dispute id
	 * @return the dispute
	 */
	Dispute findById(@Param("dispute_id") int dispute_id);

	/* (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	Dispute save(Dispute dispute);
	
    /* (non-Javadoc)
     * @see org.springframework.data.repository.CrudRepository#delete(java.lang.Object)
     */
    void delete(Dispute deleted);
}
