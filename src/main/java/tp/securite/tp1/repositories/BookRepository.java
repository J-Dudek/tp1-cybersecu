package tp.securite.tp1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tp.securite.tp1.model.Book;
import tp.securite.tp1.model.User;

import java.util.List;


public interface BookRepository extends JpaRepository<Book, Long> {
    boolean existsById(Long id);
    List<Book>findBooksByTitre(String titre);
    List<Book> findBooksByAuteur(String auteur);
    List<Book> findAllByUsersIs(User user);

}
