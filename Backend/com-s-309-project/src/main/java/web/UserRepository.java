package web;

import java.util.Collection;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

	Collection<User> findAll();

	User findByNetID(@Param("netID") String netID);

	User save(User user);

}
