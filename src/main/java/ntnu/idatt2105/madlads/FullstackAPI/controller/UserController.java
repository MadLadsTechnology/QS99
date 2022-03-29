package ntnu.idatt2105.madlads.FullstackAPI.controller;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import ntnu.idatt2105.madlads.FullstackAPI.model.repositories.UserRepository;
import ntnu.idatt2105.madlads.FullstackAPI.model.users.QSUser;
import ntnu.idatt2105.madlads.FullstackAPI.security.PasswordHashing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@EnableAutoConfiguration
@RequestMapping("/user")
public class UserController {
    public static String keyStr = "testsecrettestsecrettestsecrettestsecrettestsecret";
    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserRepository userRepository;

    @PostMapping(value = "/login")
    @ResponseStatus(value = HttpStatus.CREATED)
    public String generateToken(@RequestParam("email") final String email,
                                @RequestParam("password") final String password) throws NoSuchAlgorithmException, InvalidKeySpecException {

        QSUser foundUser = userRepository.findByEmailAddress(email);
        if(foundUser != null){
            if (email.equals(foundUser.getEmailAddress()) && PasswordHashing.validatePassword(password, foundUser.getPassword())) {
                logger.info("Logged in successfully");
                return generateToken(email);
            }
            logger.info("Wrong password");
            return "Wrong password";
        }
        return "Access denied, wrong credentials....";
    }

    public String generateToken(String userId) {
        Key key = Keys.hmacShaKeyFor(keyStr.getBytes(StandardCharsets.UTF_8));
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");

        Claims claims = Jwts.claims().setSubject(userId);
        claims.put("userId", userId);
        claims.put("authorities", grantedAuthorities
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()));

        return Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .setSubject(userId)
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000000))
                .signWith(key)
                .compact();
    }

    @PostMapping("/register")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<QSUser> createUser(@RequestParam("firstname") final String firstname,
                                             @RequestParam("lastname") final String lastname,
                                             @RequestParam("email") final String email,
                                             @RequestParam("password") final String password) {
        logger.info("email: " + email + " password: " + password);
        try {
            String hashedPassword = PasswordHashing.generatePasswordHash(password);
            QSUser QSUser = userRepository
                    .save(new QSUser(firstname, lastname, email, hashedPassword));
            return new ResponseEntity<>(QSUser, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/delete")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<User> deleteUser(@RequestParam("email") final String email){
        User foundUser = userRepository.findByEmailAddress(email);
        if(foundUser != null){
            if(email.equals(foundUser.getEmailAddress())){
                userRepository.delete(foundUser);
                logger.info("User " + email + " removed");
                return new ResponseEntity<>(foundUser, HttpStatus.OK);
            } else {
                logger.info("User not removed");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        logger.info("No user with the given email");
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
