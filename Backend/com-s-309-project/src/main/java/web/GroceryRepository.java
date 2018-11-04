package web;

import java.util.Collection;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

@Repository
public interface GroceryRepository extends CrudRepository<Grocery, Integer> {

	Collection<Grocery> findAll();

	Grocery findById(@Param("grocery_id") int grocery_id);

	Grocery save(Grocery item);
	
    void delete(Grocery deleted);
    

}
