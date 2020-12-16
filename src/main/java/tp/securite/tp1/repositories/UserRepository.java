package tp.securite.tp1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tp.securite.tp1.model.User;

import javax.transaction.Transactional;

public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsByUsername(String username);

    User findByUsername(String username);

    @Transactional
    void deleteByUsername(String username);
}
