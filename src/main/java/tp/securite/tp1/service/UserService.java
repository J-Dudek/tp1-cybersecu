package tp.securite.tp1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tp.securite.tp1.exception.CustomException;
import tp.securite.tp1.model.User;
import tp.securite.tp1.repositories.BookRepository;
import tp.securite.tp1.repositories.UserRepository;
import tp.securite.tp1.security.JwtTokenProvider;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    private static final String PASS_RGEX="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[€@#$%^&+=])(?=\\S+$).{8,20}$";

    public String signin(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            return jwtTokenProvider.createToken(username, userRepository.findByUsername(username).getRoles());
        } catch (AuthenticationException e) {
            throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public String signup(User user) {
        if (!userRepository.existsByUsername(user.getUsername())) {
            if(isValidPassword(user.getPassword())){
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                userRepository.save(user);
                return jwtTokenProvider.createToken(user.getUsername(), user.getRoles());
            }else{
                throw new CustomException("Password not valid: Must have [0-9] [a-z] [A-Z] [€@#$%^&+=] , no space and between 8 and 20 characters.", HttpStatus.CONFLICT);
            }

        } else {
            throw new CustomException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public void deleteMe(HttpServletRequest req) {
        userRepository.delete(userRepository.findByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req))));
    }

    public User search(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new CustomException("The user doesn't exist", HttpStatus.NOT_FOUND);
        }
        return user;
    }

    public User whoami(HttpServletRequest req) {
        return userRepository.findByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
    }

    public String refresh(String username) {
        return jwtTokenProvider.createToken(username, userRepository.findByUsername(username).getRoles());
    }

    public void deleteUser(String username){
        User user = userRepository.findByUsername(username);
        bookRepository.findAllByUsersIs(user).stream().map(book -> book.getUsers().remove(user));
        userRepository.deleteByUsername(username);
    }

    public void saveOrUpdate(User user){
        userRepository.save(user);
    }


     public void updatePassword(String pass,HttpServletRequest req){
         User user = userRepository.findByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
         if(isValidPassword(pass)){
             user.setPassword(passwordEncoder.encode(pass));
             userRepository.save(user);
         }else{
             throw new CustomException("Password not valid: Must have [0-9] [a-z] [A-Z] [€@#$%^&+=] , no space and between 8 and 20 characters.", HttpStatus.CONFLICT);
         }

     }

     private boolean isValidPassword(String password){
        if(password==null){
            return false;
        }else{
            Pattern p = Pattern.compile(PASS_RGEX);
            Matcher m = p.matcher(password);
            return m.matches();
        }
     }
}
