package ntnu.idatt2105.madlads.FullstackAPI.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class UserService {
    public static String keyStr = "testsecrettestsecrettestsecrettestsecrettestsecret";

    public static String generateToken(String userId, String role) {

        Key key = Keys.hmacShaKeyFor(keyStr.getBytes(StandardCharsets.UTF_8));
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(role);

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
}
