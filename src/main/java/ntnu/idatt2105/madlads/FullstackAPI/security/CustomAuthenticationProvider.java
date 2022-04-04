package ntnu.idatt2105.madlads.FullstackAPI.security;

import lombok.SneakyThrows;
import ntnu.idatt2105.madlads.FullstackAPI.model.repositories.UserRepository;
import ntnu.idatt2105.madlads.FullstackAPI.model.users.Professor;
import ntnu.idatt2105.madlads.FullstackAPI.model.users.QSUser;
import ntnu.idatt2105.madlads.FullstackAPI.model.users.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    UserRepository userRepository;

    public CustomAuthenticationProvider() {
        super();
    }

    @SneakyThrows
    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
        final String email = authentication.getName();
        final String password = authentication.getCredentials().toString();
        final List<GrantedAuthority> grantedAuths = new ArrayList<>();
        QSUser foundUser = userRepository.getDistinctByEmailAddress(email);

        if (foundUser != null) {
            if (email.equals(foundUser.getEmailAddress()) && PasswordHashing.validatePassword(password, foundUser.getPassword())) {

                if (userRepository.getDistinctByEmailAddress(email) instanceof Student) {
                    grantedAuths.add(new SimpleGrantedAuthority("STUDENT"));
                } else if (userRepository.getDistinctByEmailAddress(email) instanceof Professor) {
                    grantedAuths.add(new SimpleGrantedAuthority("PROFESSOR"));
                } else {
                    grantedAuths.add(new SimpleGrantedAuthority("ADMIN"));
                }

                final UserDetails principal = new User(email, password, grantedAuths);
                final Authentication auth = new UsernamePasswordAuthenticationToken(principal, password, grantedAuths);
                return auth;
            }
        }
        return null;
    }

    @Override
    public boolean supports(final Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
