package ntnu.idatt2105.madlads.FullstackAPI.security;

import ntnu.idatt2105.madlads.FullstackAPI.security.JWTAuthorization.JWTAuthorizationAdminFilter;
import ntnu.idatt2105.madlads.FullstackAPI.security.JWTAuthorization.JWTAuthorizationProfessorFilter;
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
        //TODO: Make database console not accessible
        http.authorizeRequests().antMatchers("/h2-ui/**").permitAll()
            .and().csrf().ignoringAntMatchers("/h2-ui/**")
            .and().headers().frameOptions().sameOrigin();

        http.csrf().disable()
                .cors().and().authorizeRequests().antMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll();

        http.authorizeRequests().antMatchers("/user/login").permitAll()
                .and().csrf().ignoringAntMatchers("/user/login")
                .and().headers().frameOptions().sameOrigin();

        http
                .csrf().disable()
                .cors().and()
                .addFilterAfter(new JWTAuthorizationUserFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers(HttpMethod.POST,"/user/login").permitAll()
                .antMatchers(HttpMethod.GET,"/subject/getByUser").authenticated()
                .antMatchers(HttpMethod.POST,"/queue/addEntry").authenticated();

        //PROFESSOR
        http
                .csrf().disable()
                .cors().and()
                .addFilterAfter(new JWTAuthorizationProfessorFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers(HttpMethod.POST,"/user/registerStudent").authenticated()
                .antMatchers(HttpMethod.POST,"/user/registerMultipleUsers").authenticated()
                .antMatchers(HttpMethod.POST,"/subject/create").authenticated()
                .antMatchers(HttpMethod.POST,"/subject/addStudent").authenticated()
                .antMatchers(HttpMethod.POST,"/subject/addStudentAssistant").authenticated()
                .antMatchers(HttpMethod.POST,"/subject/addProfessor").authenticated()
                .antMatchers(HttpMethod.GET,"/subject/**").authenticated()
                .antMatchers(HttpMethod.POST,"/queue/setQueueStatus").authenticated()
                .antMatchers(HttpMethod.POST,"/queue/create").authenticated()
                .antMatchers(HttpMethod.POST,"/exercise/**").authenticated()
                .antMatchers(HttpMethod.POST,"/user/login").authenticated()
                .antMatchers(HttpMethod.GET,"/subject/getByUser").authenticated();

        //ADMIN
        http
                .csrf().disable()
                .cors().and()
                .addFilterAfter(new JWTAuthorizationAdminFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests().antMatchers("/**").authenticated();
    }
}