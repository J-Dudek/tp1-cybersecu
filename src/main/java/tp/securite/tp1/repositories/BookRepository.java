package tp.securite.tp1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tp.securite.tp1.model.Book;
import tp.securite.tp1.model.User;

import javax.transaction.Transactional;
import java.util.List;


public interface BookRepository extends JpaRepository<Book, Long> {
    boolean existsByTitreAndAuteur(String titre,String auteur);
    boolean existsById(Long id);
    List<Book>findBooksByTitre(String titre);
    List<Book> findBooksByAuteur(String auteur);
    @Transactional
    void deleteBookByAuteurAndTitre(String auteur,String titre);
    List<Book> findAllByUsersIs(User user);

}
