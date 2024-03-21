package com.adopta.amigo.services;

import com.adopta.amigo.data.UsuarioRepository;
import com.adopta.amigo.models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario registrarUsuario(String name, String hash) {


        // Verificar si el nombre de usuario ya existe
        if (usuarioRepository.findByName(name) != null) {
            throw new RuntimeException("El nombre de usuario ya está en uso");
        }

        // Encriptar la contraseña antes de guardarla en la base de datos
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashedPassword = encoder.encode(hash);

        // Crear una instancia de Usuario con los datos proporcionados
        Usuario usuario = new Usuario();
        usuario.setName(name);
        usuario.setHash(hashedPassword);


        return usuarioRepository.save(usuario);
    }

    public Usuario login(String nombre, String hash) {
        Usuario usuario = usuarioRepository.findByName(nombre);

        if (usuario != null && passwordEncoder.matches(hash, usuario.getHash())) {
            return usuario;
        }
        return null;
    }

}

