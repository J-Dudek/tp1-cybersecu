package tp.securite.tp1.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tp.securite.tp1.model.User;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
}
