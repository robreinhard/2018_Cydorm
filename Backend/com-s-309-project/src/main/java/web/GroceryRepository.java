package web;

import java.util.Collection;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;


/**
 * The Interface GroceryRepository.
 */
@Repository
public interface GroceryRepository extends CrudRepository<Grocery, Integer> {

	/* (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	Collection<Grocery> findAll();

	/**
	 * Find by id.
	 *
	 * @param grocery_id the grocery id
	 * @return the grocery
	 */
	Grocery findById(@Param("grocery_id") int grocery_id);

	/* (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	Grocery save(Grocery item);
	
    /* (non-Javadoc)
     * @see org.springframework.data.repository.CrudRepository#delete(java.lang.Object)
     */
    void delete(Grocery deleted);
    

}
