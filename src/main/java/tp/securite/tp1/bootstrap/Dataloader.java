package tp.securite.tp1.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import tp.securite.tp1.model.Book;
import tp.securite.tp1.model.User;
import tp.securite.tp1.repositories.BookRepository;
import tp.securite.tp1.repositories.UserRepository;

@Component
public class Dataloader implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;
    @Autowired
    BookRepository bookRepository;

    @Override
    public void run(String... args) throws Exception {
        loadData();
    }


    private void loadData(){
        User user1 = new User();
        user1.setFirstname("Julien");
        user1.setName("Dudek");
        user1.setPassword("1234");
        userRepository.save(user1);

        Book book1 = new Book();
        book1.setAuteur("Bernard Werber");
        book1.setId(1L);
        book1.setTitre("Les fourmis");
        book1.setNbpages(320);
        book1.setUser(user1);
        bookRepository.save(book1);


    }
}
