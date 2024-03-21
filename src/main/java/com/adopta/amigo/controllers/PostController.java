package com.adopta.amigo.controllers;

import com.adopta.amigo.data.MascotaRepository;
import com.adopta.amigo.models.Mascota;
import com.adopta.amigo.models.Usuario;
import com.adopta.amigo.util.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Controller
public class PostController {

    @Autowired
    private UserUtils userUtils;
    @Autowired
    private MascotaRepository mascotaRepository;

    @GetMapping("/")
    public String post(Model model) {

        // Consultar todas las mascotas
        Iterable<Mascota> mascotas = mascotaRepository.findAll();

        // Obtener el usuario logueado
        Usuario username = userUtils.getUsername();

        // Agregar las mascotas al modelo para ser mostradas en la vista
        model.addAttribute("mascotas", mascotas);
        model.addAttribute("username", username);

        return "index";
    }

    @GetMapping("/detalle/{id}")
    public String detalle(@PathVariable Integer id, Model model){

        Optional<Mascota> mascotaOptional = mascotaRepository.findById(id);
        String razaName = mascotaRepository.findRazaNameByMascotaId(id);
        List<Object[]> usuarioList = mascotaRepository.findUserIdAndNameByMascotaId(id);
        List<Mascota> primerasCuatroMascotas = mascotaRepository.findFirst4ByOrderByIdAsc();

        for (Object[] usuario : usuarioList) {
            if (usuario.length > 0) {
                Object dato = usuario[0];
                System.out.println(dato);
            }
        }
        if(mascotaOptional.isPresent()){
            Mascota mascota = mascotaOptional.get();
            // Agregar la mascota al modelo para ser mostrada en la vista
            model.addAttribute("cuatromascotas", primerasCuatroMascotas);
            model.addAttribute("mascota", mascota);
            model.addAttribute("razaName", razaName);
            model.addAttribute("usuarios", usuarioList);
        } else {
            // Manejar el caso en que no se encuentre la mascota
            // Por ejemplo, redirigir a una página de error
            return "redirect:/error";
        }
        return "details-post";
    }

    @GetMapping("/postForm")
    public String postForm(Model model) {

        // Obtener el usuario logueado
        Usuario username = userUtils.getUsername();

        model.addAttribute("username", username);

        return "form-post";
    }


}
