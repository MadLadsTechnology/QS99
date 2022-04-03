package ntnu.idatt2105.madlads.FullstackAPI.security;

import ntnu.idatt2105.madlads.FullstackAPI.security.JWTAuthorization.JWTAuthorizationAdminFilter;
import ntnu.idatt2105.madlads.FullstackAPI.security.JWTAuthorization.JWTAuthorizationProfessorFilter;
import ntnu.idatt2105.madlads.FullstackAPI.security.JWTAuthorization.JWTAuthorizationUserFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Configuration
    @Order(1)
    static class DefaultWebSecurityConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .antMatcher("/user/login")
                    .authorizeRequests().anyRequest().permitAll();
        }
    }

    @Configuration
    @Order(2)
    static class DefaultWebSecurityConfig2 extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .csrf().disable().cors().and()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .authorizeRequests().anyRequest().authenticated()
                    .and()
                    .antMatcher("/**")
                    .addFilterBefore(new JWTAuthorizationAdminFilter(), FilterSecurityInterceptor.class);
        }
    }

    @Configuration
    @Order(3)
    static class DefaultWebSecurityConfig3 extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .csrf().disable()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .authorizeRequests().anyRequest().authenticated()
                    .and()
                    .antMatcher("/qs/**")
                    .addFilterBefore(new JWTAuthorizationProfessorFilter(), FilterSecurityInterceptor.class);
        }
    }

    @Configuration
    @Order(4)
    static class DefaultWebSecurityConfig4 extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .csrf().disable()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .authorizeRequests().anyRequest().authenticated()
                    .and()
                    .antMatcher("/qs/student/**")
                    .addFilterBefore(new JWTAuthorizationUserFilter(), FilterSecurityInterceptor.class);
        }
    }

}