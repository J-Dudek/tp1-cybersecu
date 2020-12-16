package tp.securite.tp1.Controllers;

import io.swagger.annotations.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tp.securite.tp1.dto.BookDataDTO;
import tp.securite.tp1.dto.UserResponseDTO;
import tp.securite.tp1.service.BookService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
@Api(tags = "books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping(value="/{titre}")
    @PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${BookController.search}", response = BookDataDTO.class, authorizations = { @Authorization(value="apiKey") })
    @ApiResponses(value = {//
                         @ApiResponse(code = 400, message = "Something went wrong"), //
                         @ApiResponse(code = 403, message = "Access denied"), //
                         @ApiResponse(code = 404, message = "The book doesn't exist"), //
                         @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public List<BookDataDTO> search(@ApiParam("titre") @PathVariable String titre){
        return bookService.search(titre).stream().map(book -> modelMapper.map(book, BookDataDTO.class)).collect(Collectors.toList());
    }

    @GetMapping(value = "/my")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    @ApiOperation(value = "${BookController.me}", response = BookDataDTO.class, authorizations = { @Authorization(value="apiKey") })
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public List<BookDataDTO> myBooks(HttpServletRequest req) {
        return bookService.myBooks(req).stream().map(book -> modelMapper.map(book,BookDataDTO.class)).collect(Collectors.toList());
    }


}
