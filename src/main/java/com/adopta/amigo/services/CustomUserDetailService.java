package com.adopta.amigo.services;

import com.adopta.amigo.data.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.adopta.amigo.models.Usuario;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("userDetailsService")
@Transactional
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        final Usuario customer = usuarioRepository.findByName(name);
        if (customer == null) {
            throw new UsernameNotFoundException(name);
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        if (customer.isSuperAdmin()) {
            System.out.println("Usuario es superadministrador");
            authorities.add(new SimpleGrantedAuthority("ROLE_SUPER_ADMIN"));
        } else {
            System.out.println("Usuario es usuario normal");
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }

        return User.withUsername(customer.getName())
                .password(customer.getHash())
                .authorities(authorities)
                .build();
    }
}

