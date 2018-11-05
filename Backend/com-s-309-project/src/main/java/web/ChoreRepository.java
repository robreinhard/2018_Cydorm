package web;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ChoreRepository extends CrudRepository<Chore, Integer> {

	Collection<Chore> findAll();

	Chore findById(@Param("chore_id") int chore_id);

	Chore save(Chore chore);
	
    void delete(Chore deleted);
}
