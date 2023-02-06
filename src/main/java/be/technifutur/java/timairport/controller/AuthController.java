package be.technifutur.java.timairport.controller;


import be.technifutur.java.timairport.model.dto.JWTHolderDTO;
import be.technifutur.java.timairport.model.form.LoginForm;
import be.technifutur.java.timairport.model.form.RegistrationForm;
import be.technifutur.java.timairport.service.UserService;
import be.technifutur.java.timairport.utils.JwtProvider;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;


    public AuthController(UserService userService, JwtProvider provider) {
        this.userService = userService;
    }


    @PostMapping("/register")
    public void register(@RequestBody @Valid RegistrationForm form){

        userService.register( form );

    }


    @PostMapping("/sign_in")
    public JWTHolderDTO login(@RequestBody @Valid LoginForm form){

        return userService.login( form );


    }

}
