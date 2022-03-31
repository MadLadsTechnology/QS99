package ntnu.idatt2105.madlads.FullstackAPI.security.JWTAuthorization;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import ntnu.idatt2105.madlads.FullstackAPI.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.List;
import java.util.stream.Collectors;

public class JWTAuthorizationFilter {
    private final static String HEADER = "Authorization";



    public static void doFiltering(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain, String role) throws IOException {
        Logger logger = LoggerFactory.getLogger(JWTAuthorizationFilter.class);
        try {
            Key key = Keys.hmacShaKeyFor(UserService.keyStr.getBytes(StandardCharsets.UTF_8));

            // expects JWT in the header
            String authenticationHeader = request.getHeader(HEADER);
            final String PREFIX = "Bearer ";

            // check Authorization header exists
            if (authenticationHeader == null || !authenticationHeader.startsWith(PREFIX)){
                SecurityContextHolder.clearContext();
            } else {

                // get token and claims
                String jwtToken = request.getHeader(HEADER).replace(PREFIX, "");
                Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwtToken);
                String gottenRole = claims.getBody().get("authorities").toString().replace("[","").replace("]","");
                if(gottenRole.equals(role)){
                    logger.info("Authenticated with Role User");
                    if (claims.getBody().get("authorities") != null) {
                        // setup Spring authentication
                        List<String> authorities = (List) claims.getBody().get("authorities");
                        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(claims.getBody().getSubject(), null,
                                authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
                        SecurityContextHolder.getContext().setAuthentication(auth);
                    } else {
                        SecurityContextHolder.clearContext();
                    }
                }
                // perform necessary checks
            }
            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException e) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
