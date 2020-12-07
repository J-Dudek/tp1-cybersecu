package tp.securite.tp1.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tp.securite.tp1.model.Book;

@Repository
public interface BookRepository extends CrudRepository<Book,Long> {
}
