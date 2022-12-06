package com.johnson.spring.security;

import com.johnson.spring.model.entities.Person;
import com.johnson.spring.model.entities.Role;
import com.johnson.spring.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Component
public class MyAuthProvider implements AuthenticationProvider {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String pass = authentication.getCredentials().toString();
        Person person = personRepository.readByEmail(email);
        if (person != null
                && passwordEncoder.matches(pass, person.getPwd())) {
            return new UsernamePasswordAuthenticationToken(person.getName(), pass, getGrantedAuthorities(person.getRole()));
        }
        throw new BadCredentialsException("credenciales inválidas");
    }

    private List<GrantedAuthority> getGrantedAuthorities(Role role) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        return authorities;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
