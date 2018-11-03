package web;

import java.util.Collection;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

@Repository
public interface LocationRepository extends CrudRepository<Location, Integer> {

	Collection<Location> findAll();
	
	Location findByLocation(@Param("location") String location);
	
	Location save(Location location);
}
