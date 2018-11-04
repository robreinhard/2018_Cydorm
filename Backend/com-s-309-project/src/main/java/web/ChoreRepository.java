package web;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ChoreRepository extends CrudRepository<Chores, Integer> {

	Collection<Chores> findAll();

	Chores findById(@Param("chore_id") int chore_id);

	Chores save(Chores chore);
	
    void delete(Chores deleted);
}
