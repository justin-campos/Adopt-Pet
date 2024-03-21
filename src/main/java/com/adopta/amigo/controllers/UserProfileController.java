package com.adopta.amigo.controllers;

import com.adopta.amigo.data.UserProfileRepository;
import com.adopta.amigo.models.Usuario;
import com.adopta.amigo.models.UsuarioDetalle;
import com.adopta.amigo.util.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class UserProfileController {

    @Autowired
    UserProfileRepository userProfileRepository;


    @Autowired
    private UserUtils userUtils;

    @GetMapping("/perfil")
    public String perfilForm(Model model){
        // Obtener el usuario logueado
        Usuario username = userUtils.getUsername();
        List<UsuarioDetalle> detalles = userProfileRepository.findUserProfileByUserId(Math.toIntExact(username.getId()));
        System.out.println("detalles: " + detalles);

        model.addAttribute("detalles", detalles);
        model.addAttribute("username", username);
        return "form-profile";
    }
    @GetMapping("/perfil/{id}")
    public String perfil(@PathVariable Integer id, Model model){
        List<UsuarioDetalle> detalles = userProfileRepository.findUserProfileByUserId(id);
        model.addAttribute("detalles", detalles);
        return "profile";
    }
}

