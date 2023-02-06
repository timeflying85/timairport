package be.technifutur.java.timairport.model.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table( name = "\"user\"" )
@Getter
@Setter
public class User implements UserDetails {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "user_id" )
    private long id;

    @Column( nullable = false, unique = true )
    private String userName;

    @Column( nullable = false )
    private String password;

    @Column( nullable = false )
    private boolean enable = true;


    @ElementCollection
    private Set<String> roles = new LinkedHashSet<>();


    // "ROLE_" ( = Authority )
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map (SimpleGrantedAuthority::new )
                .toList();
    }


    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
