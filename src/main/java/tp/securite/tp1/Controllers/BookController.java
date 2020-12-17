package tp.securite.tp1.Controllers;

import io.swagger.annotations.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tp.securite.tp1.dto.BookDataDTO;
import tp.securite.tp1.service.BookService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
@Api(tags = "BOOKS")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private ModelMapper modelMapper;


    @GetMapping(value = "/my")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    @ApiOperation(value = "${BookController.myBooks}", response = BookDataDTO.class, authorizations = { @Authorization(value="apiKey") })
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public List<BookDataDTO> myBooks(HttpServletRequest req) {
        return bookService.myBooks(req).stream().map(book -> modelMapper.map(book,BookDataDTO.class)).collect(Collectors.toList());
    }

    @DeleteMapping(value ="/remvove/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    @ApiOperation(value = "${BookController.removeBook}", authorizations = { @Authorization(value="apiKey") })
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"),
            @ApiResponse(code = 422,message = "You haven't this book"),//
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public void removeBook(@ApiParam("id Book") @PathVariable Long id,HttpServletRequest req) {
         bookService.removeBook(id,req);
    }

}
