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
    private static final String ROLE_USER = "applicant";
    private static final String ROLE_ADMIN = "recruiter";

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(encoder());
    }

    /**
     * Configure Authorization to URLs and Login/logout logic.
     * @param http object that builds specified security configuration
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                        .antMatchers("/welcome", "/login-user", "/sign-up").permitAll()
                        .antMatchers("/apply").hasAuthority(ROLE_USER)
                        .anyRequest().authenticated()
                        .and()

                .formLogin()
                        .loginPage("/login-user").permitAll()
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/welcome", true)
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

