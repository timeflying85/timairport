package be.technifutur.java.timairport.service;


import be.technifutur.java.timairport.model.form.RegistrationForm;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    void register(RegistrationForm form);

}
