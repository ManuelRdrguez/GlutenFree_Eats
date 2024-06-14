package com.example.glutenfree.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.glutenfree.dto.UserRegistro;
import com.example.glutenfree.entities.Reseña;
import com.example.glutenfree.entities.Restaurante;
import com.example.glutenfree.entities.Usuario;
import com.example.glutenfree.service.ReseñaService;
import com.example.glutenfree.service.RestauranteService;
import com.example.glutenfree.service.UserService;


@Controller
public class RegistroController {
	  @Autowired
	    private PasswordEncoder passwordEncoder;
	@Autowired
	private UserService servicio;
	 @Autowired
	    private RestauranteService restauranteService;
	 @Autowired
	    private ReseñaService reseñaService;
	@GetMapping("/login")
	public String iniciarSesion() {
		return "login";
	}
	@GetMapping("user/home")
	public String verPaginaDeInicio(Model modelo) {
		return "index";
	}
	@GetMapping("/restaurante")
	public String verPaginaDeRestaurante(Model modelo) {
		return "auth/user/restaurante";
	}
	@GetMapping("/contact")
	public String verPaginaDeContacto(Model modelo) {
		return "auth/user/contact";
	}
	
	 @GetMapping("/ver_perfil")
	    public String verPaginaDeVerPerfil(Model modelo) {
	        // Obtener el objeto de autenticación del usuario actual
	        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        
	        // Obtener el nombre de usuario del objeto de autenticación
	        String nombreUsuario = auth.getName();
	        
	        // Obtener la información del usuario desde la base de datos utilizando el nombre de usuario
	        Usuario usuario = servicio.findByUsername(nombreUsuario);
	        
	        // Añadir el usuario al modelo para pasarlo a la vista
	        modelo.addAttribute("usuario", usuario);
	        
	        return "auth/user/ver_perfil";
	    }
	 
	 @GetMapping("/editar_perfil")
	 public String verpaginadeeditarperfil(Model modelo) {
		    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		    String nombreUsuarioAutenticado = auth.getName();
		    Usuario usuario = servicio.findByUsername(nombreUsuarioAutenticado);
	        modelo.addAttribute("usuario", usuario);

			return "auth/user/editar_perfil";
		}
	 @PostMapping("/editar_perfil")
	    public String guardarCambiosPerfil(@RequestParam("username") String nombre,
	                                       @RequestParam("apellido") String apellido,
	                                       @RequestParam("password") String nuevaPassword) {
	        // Obtenemos el usuario autenticado desde el contexto de seguridad
	        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        String nombreUsuario = auth.getName();
	        Usuario usuario = servicio.findByUsername(nombreUsuario);
	        
	        // Actualizamos los datos del usuario
	        usuario.setUsername(nombre);
	        usuario.setApellido(apellido);
	        
	        // Encriptamos la nueva contraseña y actualizamos si se proporcionó
	        if (!nuevaPassword.isEmpty()) {
	            String passwordEncriptada = passwordEncoder.encode(nuevaPassword);
	            usuario.setPassword(passwordEncriptada);
	        }
	        
	        // Guardamos los cambios en la base de datos
	        servicio.save(usuario);
	        // Redireccionar a una página de confirmación o a donde sea necesario
	        return "redirect:/ver_perfil";
	    }
	
	@GetMapping("/ver_reseñas")
	public String verPaginaDeVerReseñas(Model modelo) {
		return "auth/user/ver_reseñas";
	}
	@GetMapping("/restaurantesUser")
    public String getAllRestaurantes(Model model) {
        List<Restaurante> restaurantes = restauranteService.findAll();
        model.addAttribute("restaurantes", restaurantes);
        return "auth/user/restaurantes_visitados";
    }
	
	@GetMapping("/crearRestauranteUser")
    public String createRestauranteForm(Model model) {
        model.addAttribute("restaurante", new Restaurante());
        return "auth/user/crear_restaurante_user";
    }

    @PostMapping("/crearRestauranteUser")
    public String saveRestaurante(@ModelAttribute Restaurante restaurante, Model model) {
        restauranteService.save(restaurante);
        List<Restaurante> restaurantes = restauranteService.findAll();
        model.addAttribute("restaurantes", restaurantes);
        return "redirect:/restaurantes_visitados";
    }
    
    @GetMapping("/ver_resenas_user")
    public String listarReseñas(Model model) {
    	  // Obtener el usuario autenticado
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        // Encontrar el usuario por su nombre de usuario
        Usuario usuario = servicio.findByUsername(currentUsername);
        if (usuario == null) {
            // Usuario no encontrado, mostrar mensaje de error
            return "redirect:/ver_resenas_user?error=Usuario no encontrado";
        }

        // Obtener las reseñas del usuario autenticado
        List<Reseña> reseñas = reseñaService.findByUser(usuario);

        // Añadir las reseñas al modelo
        model.addAttribute("reseñas", reseñas);

        return "auth/user/ver_reseñas";
    }
    
    
    @GetMapping("/resenaNuevaUser")
    public String showCreateReseñaForm(Model model) {
    	  // Obtener el usuario autenticado
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        // Encontrar el usuario por su nombre de usuario
        Usuario usuario = servicio.findByUsername(currentUsername);
        if (usuario == null) {
            // Usuario no encontrado, mostrar mensaje de error
            return "redirect:/resenaNuevaUser?error=Usuario no encontrado";
        }

        // Obtener las reseñas del usuario autenticado
        List<Reseña> reseñas = reseñaService.findByUser(usuario);

        // Añadir las reseñas y otros atributos al modelo
        model.addAttribute("reseñas", reseñas);
        List<Restaurante> restaurantes = restauranteService.findAll();
        model.addAttribute("restaurantes", restaurantes);
        model.addAttribute("reseña", new Reseña());

        return "auth/user/crear_resena_user";
    }

    @PostMapping("/resenaNuevaUser")
    public String createReseña(@ModelAttribute("reseña") Reseña reseña) {
    	// Obtener el usuario autenticado
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        // Encontrar el usuario por su nombre de usuario
        Usuario usuario = servicio.findByUsername(currentUsername);
        if (usuario == null) {
            // Usuario no encontrado, mostrar mensaje de error
            return "redirect:/resenaNuevaUser?error=Usuario no encontrado";
        }

        // Asociar la reseña con el usuario autenticado
        reseña.setUsuario(usuario);
        reseñaService.createReseña(reseña);

        return "redirect:/resenaNuevaUser?success";
    }
    
    @GetMapping("/resenas/delete/{id}")
    public String eliminarReseña(@PathVariable("id") Long id) {
        reseñaService.deleteById(id);
        return "redirect:/ver_resenas_user";
    }

}
