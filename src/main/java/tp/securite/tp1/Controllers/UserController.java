package tp.securite.tp1.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tp.securite.tp1.model.User;
import tp.securite.tp1.repositories.UserRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/users")
    public List<User> getAll(){
        return StreamSupport.stream((userRepository.findAll()).spliterator(), false)
                .collect(Collectors.toList());
    }
}
