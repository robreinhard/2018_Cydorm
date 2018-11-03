package web;

import java.util.Collection;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

@Repository
public interface AddressRepository extends CrudRepository<Address, Integer> {

	Collection<Address> findAll();

	Address findByAddress(@Param("address") String address);

	Address save(Address addresss);

}
