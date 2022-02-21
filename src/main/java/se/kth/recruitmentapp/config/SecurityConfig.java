package se.kth.recruitmentapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Contains configuration for Spring Security
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String ROLE_APPLICANT = "applicant";
    private static final String ROLE_RECRUITER = "recruiter";

    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * Configuration, retrieve user details.
     * @param auth this parameter uses userDetailsService and the password encoder to configure authentication.
     * @throws Exception throws any exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(encoder());
    }

    /**
     * Configure Authorization to URLs and Login/logout logic.
     * @param http object that builds specified security configuration
     * @throws Exception throws any exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                        .antMatchers("/recruitment", "/recruitment/**").hasAuthority(ROLE_RECRUITER)
                        .antMatchers("/apply", "/add-competence").hasAuthority(ROLE_APPLICANT)
                        .antMatchers("/", "/**").permitAll()
                        .anyRequest().authenticated()
                        .and()
                .exceptionHandling()
                        .accessDeniedPage("/login-user")
                        .and()
                .formLogin()
                        .loginPage("/login-user").permitAll()
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/login-success", true)
                        .and()
                .logout()
                        .permitAll();
    }

    /**
     * Bean that defines a password encoder.
     * @return An instance of BCryptPasswordEncoder
     */
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}

