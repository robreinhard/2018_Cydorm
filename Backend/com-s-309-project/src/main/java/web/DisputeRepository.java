package web;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DisputeRepository extends CrudRepository<Dispute, Integer> {
	Collection<Dispute> findAll();

	Dispute findById(@Param("dispute_id") int dispute_id);

	Dispute save(Dispute dispute);
	
    void delete(Dispute deleted);
}
