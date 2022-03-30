package ntnu.idatt2105.madlads.FullstackAPI.security.JWTAuthorization;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthorizationAdminFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException {
        JWTAuthorizationFilter.doFiltering(request, response, filterChain, "ROLE_ADMIN");
    }
}
