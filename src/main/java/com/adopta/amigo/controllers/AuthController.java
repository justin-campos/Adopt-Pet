package com.adopta.amigo.controllers;

import com.adopta.amigo.models.Usuario;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.adopta.amigo.services.UsuarioService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;


    @GetMapping("/registro")
    public String mostrarFormularioRegistro() {
        return "registro";
    }

    @PostMapping("/registro")
    public Usuario registrar(RedirectAttributes redirectAttributes,
                             @RequestParam("name") String name,
                             @RequestParam("hash") String hash) {

        return usuarioService.registrarUsuario(name, hash);
    }


    @GetMapping("/login")
    public String mostrarFormularioLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String login(
                        @RequestParam String name,
                        @RequestParam String hash) {
        System.out.println("name, hash"+ name + hash);
        Usuario usuario = usuarioService.login(name, hash);

        if(usuario != null){
            return "redirect:/";
        }

        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        model.addAttribute("message", "Sesion salida correctamente");
        return "redirect:/login?logout";
    }

}

