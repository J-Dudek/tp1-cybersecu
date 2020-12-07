package tp.securite.tp1.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tp.securite.tp1.model.Book;
import tp.securite.tp1.repositories.BookRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class BookController {
    @Autowired
    BookRepository bookRepository;

    @GetMapping("/admin/allbooks")
    public List<Book> getAllBookForAdmin(){
        return StreamSupport.stream((bookRepository.findAll()).spliterator(), false)
                .collect(Collectors.toList());
    }
}
