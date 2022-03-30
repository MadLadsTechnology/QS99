package ntnu.idatt2105.madlads.FullstackAPI.security;

import ntnu.idatt2105.madlads.FullstackAPI.security.JWTAuthorization.JWTAuthorizationAdminFilter;
import ntnu.idatt2105.madlads.FullstackAPI.security.JWTAuthorization.JWTAuthorizationUserFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // token endpoint is not protected
        http.authorizeRequests().antMatchers("/h2-ui/**").permitAll()
            .and().csrf().ignoringAntMatchers("/h2-ui/**")
            .and().headers().frameOptions().sameOrigin();

        http
            .csrf().disable()
            .cors().and()
            .addFilterAfter(new JWTAuthorizationUserFilter(), UsernamePasswordAuthenticationFilter.class)
            .authorizeRequests()
            .antMatchers(HttpMethod.POST, "/user/**").permitAll()
            .antMatchers(HttpMethod.DELETE, "/user/**").permitAll()
            .antMatchers(HttpMethod.GET, "/swagger-ui/**").permitAll()
            .antMatchers(HttpMethod.POST, "/subject/**").authenticated()
            .antMatchers(HttpMethod.GET,"/v3/**").authenticated()
            .antMatchers(HttpMethod.POST,"/queue/**").permitAll()
            .antMatchers(HttpMethod.POST, "/subject/**").permitAll()
            .antMatchers(HttpMethod.GET,"/queue/**").authenticated()
            .antMatchers(HttpMethod.POST, "/subject/**").permitAll()
            .antMatchers(HttpMethod.GET, "/subject/**").permitAll();

        http
            .csrf().disable()
            .cors().and()
            .addFilterAfter(new JWTAuthorizationAdminFilter(), UsernamePasswordAuthenticationFilter.class)
            .authorizeRequests() .antMatchers("/admin/**").authenticated();
    }
}