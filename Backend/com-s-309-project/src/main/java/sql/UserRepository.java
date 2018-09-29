package sql;

import java.util.Collection;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
import sql.CyDormUser;

@Repository
public interface UserRepository extends CrudRepository<CyDormUser, Integer> {

	Collection<CyDormUser> findAll();

	Collection<CyDormUser> findById(@Param("id") int id);

	CyDormUser save(CyDormUser user);

}
