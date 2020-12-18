package tp.securite.tp1.Controllers;

import io.swagger.annotations.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tp.securite.tp1.dto.BookDataDTO;
import tp.securite.tp1.dto.BookLightDTO;
import tp.securite.tp1.model.Book;
import tp.securite.tp1.service.BookService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/library")
@Api(tags = "LIBRARY")
public class LibraryController {

    @Autowired
    private BookService bookService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping(value="/{titre}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${LibraryController.search}", response = BookDataDTO.class, authorizations = { @Authorization(value="apiKey") })
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 404, message = "The book doesn't exist"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public List<BookDataDTO> search(@ApiParam("titre") @PathVariable String titre){
        return bookService.search(titre).stream().map(book -> modelMapper.map(book, BookDataDTO.class)).collect(Collectors.toList());
    }

    @GetMapping(value="/all")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${LibraryController.listAll}", response = BookDataDTO.class, authorizations = { @Authorization(value="apiKey") })
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 404, message = "The book doesn't exist"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public List<BookDataDTO> listAll(){
        return bookService.listAll().stream().map(book -> modelMapper.map(book, BookDataDTO.class)).collect(Collectors.toList());
    }

    @PatchMapping(value = "/addBook/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    @ApiOperation(value = "${LibraryController.addBook}", response = BookDataDTO.class, authorizations = { @Authorization(value="apiKey") })
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 422, message = "No book with this id"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public List<BookDataDTO> addBook(@ApiParam("id Book") @PathVariable Long id, HttpServletRequest req){
        return bookService.addBook(req,id).stream().map(book -> modelMapper.map(book,BookDataDTO.class)).collect(Collectors.toList());
    }

    @PatchMapping(value = "/add/")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${LibraryController.addNewBook}", authorizations = { @Authorization(value="apiKey") })
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public void addNewBook(@ApiParam("The new Book") @RequestBody BookLightDTO bookLightDTO, HttpServletRequest req){
        bookService.addnewBook(modelMapper.map(bookLightDTO, Book.class));
    }

    @PatchMapping(value = "/addList/")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${LibraryController.addNewBooks}", authorizations = { @Authorization(value="apiKey") })
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public void addNewBooks(@ApiParam("List of new Book") @RequestBody List<BookLightDTO> list, HttpServletRequest req){
        bookService.addNewBooks(list.stream().map(bookDataDTO -> modelMapper.map(bookDataDTO,Book.class)).collect(Collectors.toList()));
    }

}
