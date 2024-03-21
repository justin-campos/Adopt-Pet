package com.adopta.amigo.util;

import com.adopta.amigo.data.UsuarioRepository;
import com.adopta.amigo.models.Usuario;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserUtils {

        private final UsuarioRepository usuarioRepository;

        public UserUtils(UsuarioRepository usuarioRepository) {
            this.usuarioRepository = usuarioRepository;
        }

        public Usuario getUsername() {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            return usuarioRepository.findByName(username);
        }
}
