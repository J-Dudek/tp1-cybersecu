package tp.securite.tp1.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import tp.securite.tp1.model.Book;
import tp.securite.tp1.model.Role;
import tp.securite.tp1.model.User;
import tp.securite.tp1.repositories.UserRepository;
import tp.securite.tp1.service.BookService;
import tp.securite.tp1.service.UserService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class Dataloader implements CommandLineRunner {


    @Autowired
    UserService userService;
    @Autowired
    BookService bookService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        loadData();
    }


    private void loadData(){
        //ADMIN
        User admin = new User();
        admin.setUsername("Gertrude");
        admin.setPassword(passwordEncoder.encode("Cyb&rCQrizTh3"));
        admin.setEmail("admin@email.com");
        admin.setRoles(new ArrayList<>(Collections.singletonList(Role.ROLE_ADMIN)));
        userRepository.save(admin);
        //Users
        User client = new User();
        client.setUsername("user");
        client.setPassword("Cyb&rCQrizTh3");
        client.setEmail("user@email.com");
        client.setRoles(new ArrayList<>(Collections.singletonList(Role.ROLE_USER)));
        userService.signup(client);
        User client2 = new User();
        client2.setUsername("user2");
        client2.setPassword("Cyb&rCQrizTh3");
        client2.setEmail("user2@email.com");
        client2.setRoles(new ArrayList<>(Collections.singletonList(Role.ROLE_USER)));
        userService.signup(client2);
        //Books
        String[] titres = {"La cybersécurité et les décideurs","Tactique Cyber : Le combat numérique","Cyber harcèlement"};
        String[] auteurs = {"Marie de Fréminville","Arnaud LE DEZ","Bérengère Stassin"};
        int[] nbPages = {203,148,173};
        List<Book> bookList = new ArrayList<>();
        for(int i=0;i<titres.length;i++){
            Book book = new Book();
            book.setTitre(titres[i]);book.setAuteur(auteurs[i]);book.setNbpages(nbPages[i]);
            admin.getBooks().add(book);
            bookList.add(book);

        }
        bookService.saveAll(bookList);
        bookList.stream().map(book ->  admin.getBooks().add(book));
        userService.saveOrUpdate(admin);

    }
}
