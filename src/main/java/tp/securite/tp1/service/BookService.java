package tp.securite.tp1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tp.securite.tp1.model.Book;
import tp.securite.tp1.repositories.BookRepository;
import tp.securite.tp1.security.JwtTokenProvider;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    public List<Book> search(String titre){
        return bookRepository.findBooksByTitre(titre);
    }

    public void saveAll(List<Book> bookList){
        bookRepository.saveAll(bookList);
    }

}
