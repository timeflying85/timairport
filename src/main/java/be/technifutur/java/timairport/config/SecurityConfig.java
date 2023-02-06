package be.technifutur.java.timairport.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

@Configuration
@EnableWebSecurity
//@EnableMethodSecurity
public class SecurityConfig {


    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf().disable();
        http.httpBasic();
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        /**
         * Les premiers matchers ont la priorité
         * AnyRequest, s'il est mis, doit être le dernier matcher
         *
         * RequestMatchers:
         *
         *          Lambda RequestMatchers:
         *          - prend une HttpServletRequest en param, renvoi un boolean
         *
         *          Method
         *          - une valeur de l'enum HttpMethod
         *
         *          - ?  : replace one letter
         *          - *  : n'importe quelles value dans 1 segment
         *          - {machin:regex} : n'importe quelles valeurs correspondant au pattern regex pour 1 segmeny
         *          - ** : n'importe quelles value dans 0 à N segment (seulement en dernier segment)
         *
         *
         *  Authorization:
         *          -permitAll():       tout le monde passe
         *          -denyAll():         person ne passe
         *          -anonymous():       non authenticates users
         *          -authenticated():   authenticates users
         *          -hasRole(?):
         *          -hasAnyRole(...?)
         *          -hasAuthority()
         *          -hasAnyAuthority(...?)
         *
         *      Une Authority c'est un droit sous forme de String
         *      Un Role est une Authority qui commence par 'ROLE_'
         *
         */


        http.authorizeHttpRequests( (authorize) -> {
            authorize
                    // via lambda request
                    .requestMatchers(request -> request.getRequestURI().length() > 50).hasRole(("ADMIN"))
                    // via HttpMethod
                    .requestMatchers( HttpMethod.POST ).hasRole("ADMIN")
                    .requestMatchers( HttpMethod.PUT ).hasRole("ADMIN")
                    .requestMatchers( HttpMethod.PATCH ).hasRole("ADMIN")
                    .requestMatchers( HttpMethod.DELETE ).hasRole("ADMIN")
                    // via mapping d'URI
                    .requestMatchers("plane/all").anonymous()
                    .requestMatchers("/plane/add").authenticated()
                    .requestMatchers("/plane/{id:[0-9]+}/update").hasRole("ADMIN") // ou .hasAuthority("ROLE_ADMIN")
                   // via HttpMethod + mapping d'URI
                    .requestMatchers(HttpMethod.GET, "/plane/*")
                                    .hasAnyRole("USER","ADMIN")
                                    // .hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                    .requestMatchers(HttpMethod.GET,"/flight/*")
                                    .permitAll()
                    .anyRequest().permitAll();
        });

        return http.build();
    }


//    @Bean
//    public UserDetailsService userDetailsService( PasswordEncoder encoder ){
//
//        List<UserDetails> users = List.of(
//                User.builder()
//                        .username("user")
//                        .password(encoder.encode("pass"))
//                        .roles("USER")
//                        .build(),
//                User.builder()
//                        .username("admin")
//                        .password(encoder.encode("pass"))
//                        .roles("ADMIN","USER")
//                        .build()
//        );
//
//        return new InMemoryUserDetailsManager( users );
//
//    }

}

