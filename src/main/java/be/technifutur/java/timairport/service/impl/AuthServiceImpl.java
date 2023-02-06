package be.technifutur.java.timairport.service.impl;

import be.technifutur.java.timairport.exceptions.FormValidationException;
import be.technifutur.java.timairport.jwt.JWTHolderDTO;
import be.technifutur.java.timairport.model.entity.User;
import be.technifutur.java.timairport.model.form.LoginForm;
import be.technifutur.java.timairport.model.form.RegistrationForm;
import be.technifutur.java.timairport.repository.UserRepository;
import be.technifutur.java.timairport.service.AuthService;
import be.technifutur.java.timairport.jwt.JwtProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder encoder;

    private final AuthenticationManager manager;

    private final JwtProvider jwtprovider;


    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder encoder, AuthenticationManager manager, JwtProvider jwtprovider) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.manager = manager;
        this.jwtprovider = jwtprovider;
    }

    @Override
    public void register(RegistrationForm form) {

        if (userRepository.existsByUsername((form.getUsername())))
            throw new FormValidationException("this username already exists");

        User user = form.toEntity();
        user.setPassword(encoder.encode(user.getPassword()));

        userRepository.save(user);

    }

    @Override
    public JWTHolderDTO login(LoginForm form) {

        Authentication auth = new UsernamePasswordAuthenticationToken(
                form.getUsername(),
                form.getPassword()
        );

        manager.authenticate(auth);

        String token = jwtprovider.createToken(auth);

        return new JWTHolderDTO(token);

    }

}
