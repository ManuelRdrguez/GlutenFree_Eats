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

import com.example.glutenfree.entities.Peticion;
import com.example.glutenfree.entities.Reseña;
import com.example.glutenfree.entities.Restaurante;
import com.example.glutenfree.entities.Usuario;
import com.example.glutenfree.service.PeticionService;
import com.example.glutenfree.service.ReseñaService;
import com.example.glutenfree.service.RestauranteService;
import com.example.glutenfree.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	private UserService userservice;
	  @Autowired
	    private ReseñaService reseñaService;
	  @Autowired
	    private RestauranteService restauranteService;
	  
	  @Autowired
	    private PeticionService peticionService;
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
 
    
    
   

    @GetMapping("/restaurantes")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getAllRestaurantes(Model model) {
        List<Restaurante> restaurantes = restauranteService.findAll();
        model.addAttribute("restaurantes", restaurantes);
        return "auth/admin/admin_restaurantes";
    }
    
    @GetMapping("/peticiones")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getAllPeticiones(Model model) {
        List<Peticion> peticiones = peticionService.findAll();
        model.addAttribute("peticiones", peticiones);
        return "auth/admin/admin_peticiones";
    }
    @GetMapping("/peticiones/delete/{id}")
    public String eliminarPeticion(@PathVariable("id") Long id) {
        peticionService.deleteById(id);
        return "redirect:/admin/peticiones";
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
            existingRestaurante.setOpciones_celiacos(restaurante.getOpciones_celiacos());

            restauranteService.save(existingRestaurante);
        }
        return "redirect:/admin/restaurantes";
    }
    
    
    @GetMapping("/resenas")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String listarReseñas(Model model) {
        List<Reseña> reseñas = reseñaService.findAll();
        model.addAttribute("reseñas", reseñas);
        return "auth/admin/admin_reseñas";
    }
    
 
    @GetMapping("/resenaNueva")
    public String showCreateReseñaForm(Model model) {
    	List<Restaurante> restaurantes = restauranteService.findAll();
        model.addAttribute("restaurantes", restaurantes);
        model.addAttribute("reseña", new Reseña());
        return "auth/admin/crearResena";
    }

    @PostMapping("/resenaNueva")
    public String createReseña(@ModelAttribute("reseña") Reseña reseña) {
        String username = reseña.getUsuario().getUsername();
        Usuario usuario = userservice.findByUsername(username);
        if (usuario == null) {
            // Usuario no encontrado, mostrar mensaje de error y volver al formulario
            return "redirect:/admin/resenaNueva?error=Usuario no encontrado";
        }
        reseña.setUsuario(usuario);
        reseñaService.createReseña(reseña);
        return "redirect:/admin/resenaNueva?success";
    }
    @GetMapping("/resenas/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")    
    public String eliminarReseña(@PathVariable("id") Long id) {
        reseñaService.deleteById(id);
        return "redirect:/admin/resenas";
    }
}