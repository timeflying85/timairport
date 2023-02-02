package be.technifutur.java.timairport.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

@Configuration
@EnableWebSecurity
//@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf().disable();
        http.httpBasic();
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeHttpRequests( (authorize) -> {
            authorize
                    .requestMatchers("plane/all").anonymous()
                    .requestMatchers("/plane/add").authenticated()
                    .anyRequest().permitAll();
        });

        return http.build();
    }

    public UserDetailsService userDetailsService(){

        List<UserDetails> users = List.of(
                User.builder()
                        .username("user")
                        .password("pass")
                        .roles("USER")
                        .build(),
                User.builder()
                        .username("admin")
                        .password("pass")
                        .roles("ADMIN","USER")
                        .build()
        );

        return new InMemoryUserDetailsManager( users );

    }

}

