package web;

import java.util.Collection;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

@Repository
public interface SublocationRepository extends CrudRepository<Sublocation, Integer> {

	Collection<Sublocation> findAll();
	
	Sublocation findBySublocation(@Param("sublocation") String sublocation);
	
	Sublocation save(Sublocation sublocation);
}
