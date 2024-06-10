package com.example.glutenfree.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.glutenfree.entities.Reseña;
import com.example.glutenfree.entities.Restaurante;
import com.example.glutenfree.entities.Usuario;
import com.example.glutenfree.service.ReseñaService;
import com.example.glutenfree.service.RestauranteService;
import com.example.glutenfree.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	private UserService userservice;
	  @Autowired
	    private ReseñaService reseñaService;

	public AdminController(UserService usuarioServicio) {
		super();
		this.userservice = usuarioServicio;
		this.reseñaService= reseñaService;
	}
    @GetMapping("/home")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String admin() {
        return "auth/admin/index"; // Muestra la página específica del administrador (admin.html)
    }

    
  
    
    @GetMapping("/usuarios")
	  public String listarUsuarios(Model model) {
	        List<Usuario> usuarios = userservice.listarUsuarios();
	        model.addAttribute("usuarios", usuarios);
	        return "auth/admin/usuarios"; // nombre del archivo HTML (listarUsuarios.html)
	    }
 
    
    
    @Autowired
    private RestauranteService restauranteService;

    @GetMapping("/restaurantes")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getAllRestaurantes(Model model) {
        List<Restaurante> restaurantes = restauranteService.findAll();
        model.addAttribute("restaurantes", restaurantes);
        return "auth/admin/admin_restaurantes";
    }

    
    @GetMapping("/crearRestaurante")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String createRestauranteForm(Model model) {
        model.addAttribute("restaurante", new Restaurante());
        return "auth/admin/crearRestaurante";
    }

    @PostMapping("/crearRestaurante")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String saveRestaurante(@ModelAttribute Restaurante restaurante, Model model) {
        restauranteService.save(restaurante);
        List<Restaurante> restaurantes = restauranteService.findAll();
        model.addAttribute("restaurantes", restaurantes);
        return "redirect:/admin/restaurantes";
    }

    @GetMapping("/restaurantes/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteRestaurante(@PathVariable Long id,Model model) {
        restauranteService.deleteById(id);
        List<Restaurante> restaurantes = restauranteService.findAll();
        model.addAttribute("restaurantes", restaurantes);
        return "redirect:/admin/restaurantes";
    }
    
    
    @GetMapping("restaurantes/edit/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String editRestauranteForm(@PathVariable Long id, Model model) {
        Restaurante restaurante = restauranteService.findById(id).orElse(null);
        if (restaurante != null) {
            model.addAttribute("restaurante", restaurante);
            return "auth/admin/editar_restaurante";
        } else {
            return "redirect:/admin/restaurantes";
        }
    }

    @PostMapping("restaurantes/edit/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String updateRestaurante(@PathVariable Long id, @ModelAttribute Restaurante restaurante, Model model) {
        Restaurante existingRestaurante = restauranteService.findById(id).orElse(null);
        if (existingRestaurante != null) {
            existingRestaurante.setNombre(restaurante.getNombre());
            existingRestaurante.setDescripcion(restaurante.getDescripcion());
            existingRestaurante.setDireccion(restaurante.getDireccion());
            existingRestaurante.setHorario(restaurante.getHorario());
            existingRestaurante.setTelefono(restaurante.getTelefono());
            restauranteService.save(existingRestaurante);
        }
        return "redirect:/admin/restaurantes";
    }
    
    
    @GetMapping("/reseñas")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String listarReseñas(Model model) {
        List<Reseña> reseñas = reseñaService.findAll();
        model.addAttribute("reseñas", reseñas);
        return "auth/admin/admin_reseñas";
    }
    
    @GetMapping("/reseñaNueva")
    public String mostrarFormularioReseña(Model model) {
        List<Restaurante> restaurantes = restauranteService.findAll();
        model.addAttribute("restaurantes", restaurantes);
        model.addAttribute("reseña", new Reseña());
        return "auth/admin/crearResena";
    }
    @PostMapping("/reseñaNueva")
    @PreAuthorize("hasRole('ROLE_ADMIN')")

    public String crearReseña(@ModelAttribute("reseña") Reseña reseña) {
    	String nombreRestaurante = reseña.getRestaurante().getNombre(); // Obtener el nombre del restaurante desde el objeto Reseña
        reseñaService.crearReseña(reseña.getComentario(), reseña.getPuntuacion(), reseña.getRestaurante().getNombre(), reseña.getUsuario().getUsername());
        return "redirect:/admin/reseñas";
    }
}