package web;

import java.util.Collection;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

/**
 * The Interface AddressRepository.
 */
@Repository
public interface AddressRepository extends CrudRepository<Address, Integer> {

	/* (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	Collection<Address> findAll();

	/**
	 * Find by address.
	 *
	 * @param address the address
	 * @return the address
	 */
	Address findByAddress(@Param("address") String address);

	/* (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	Address save(Address addresss);

}
