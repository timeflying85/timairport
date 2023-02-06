package be.technifutur.java.timairport.service.impl;

import be.technifutur.java.timairport.exceptions.FormValidationException;
import be.technifutur.java.timairport.model.dto.JWTHolderDTO;
import be.technifutur.java.timairport.model.entity.User;
import be.technifutur.java.timairport.model.form.LoginForm;
import be.technifutur.java.timairport.model.form.RegistrationForm;
import be.technifutur.java.timairport.repository.UserRepository;
import be.technifutur.java.timairport.service.UserService;
import be.technifutur.java.timairport.utils.JwtProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder encoder;

    private final AuthenticationManager manager;

    private final JwtProvider jwtprovider;


    public UserDetailsServiceImpl(UserRepository userRepository, PasswordEncoder encoder, AuthenticationManager manager, JwtProvider provider, JwtProvider jwtprovider) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.manager = manager;
        this.jwtprovider = jwtprovider;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow( () -> new UsernameNotFoundException("Bad Credentials"));

    }


    @Override
    public void register(RegistrationForm form) {

        if ( userRepository.existsByUsername( ( form.getUsername() ) ) )
            throw new FormValidationException("this username already exists");

        User user = form.toEntity();
        user.setPassword( encoder.encode( user.getPassword() ) );

        userRepository.save( user );

    }

    @Override
    public JWTHolderDTO login(LoginForm form) {

        Authentication auth = new UsernamePasswordAuthenticationToken(
                form.getUsername(),
                form.getPassword()
        );

        manager.authenticate( auth );

        String token = jwtprovider.createToken( auth );

        return new JWTHolderDTO( token );

    }

}
