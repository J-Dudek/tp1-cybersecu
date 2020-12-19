package tp.securite.tp1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import tp.securite.tp1.exception.CustomException;
import tp.securite.tp1.model.Book;
import tp.securite.tp1.model.User;
import tp.securite.tp1.repositories.BookRepository;
import tp.securite.tp1.repositories.UserRepository;
import tp.securite.tp1.security.JwtTokenProvider;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserRepository userRepository;


    public List<Book> search(String titre){
        return bookRepository.findBooksByTitre(titre);
    }

    public void saveAll(List<Book> bookList){
        bookRepository.saveAll(bookList);
    }

    public List<Book> myBooks(HttpServletRequest req){
        User user = userRepository.findByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
        return bookRepository.findAllByUsersIs(user);
    }

    public List<Book> listAll() { return bookRepository.findAll();}

    public List<Book> addBook(HttpServletRequest reg, Long idBook){
        User user = userRepository.findByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(reg)));
        Optional<Book> book = bookRepository.findById(idBook);
        if(book.isPresent()){
            Book book1= book.get();
            user.getBooks().add(book1);
            userRepository.save(user);
            return myBooks(reg);
        }else{
            throw new CustomException("Book is'nt available in the library", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public void removeBook(Long idBook,HttpServletRequest reg ){
        User user = userRepository.findByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(reg)));
        Optional<Book> book = bookRepository.findById(idBook);
        if(!book.isPresent()){
            throw new CustomException("This book not exist",HttpStatus.UNPROCESSABLE_ENTITY);
        }else{
            Optional<Book> theBook = user.getBooks().stream().filter(book1 -> book1.getId().equals(idBook)).findFirst();
            if(!theBook.isPresent()){
                throw new CustomException("You Haven't this book",HttpStatus.NO_CONTENT);
            }else{
                user.getBooks().remove(theBook.get());
                userRepository.save(user);
            }
        }

    }

    public void addnewBook(Book book){
        bookRepository.save(book);
    }
    public void addNewBooks(List<Book> bookList){
        bookList.forEach(book -> addnewBook(book));
    }

    public void updateBook(Book book){
        Optional<Book> bookCible = bookRepository.findById(book.getId());
        if(bookCible.isPresent()){
            bookRepository.save(book);
        }else{
            throw new CustomException("This id don't exist",HttpStatus.UNPROCESSABLE_ENTITY);
        }

    }
}
