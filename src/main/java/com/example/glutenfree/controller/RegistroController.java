package com.example.glutenfree.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.glutenfree.dto.UserRegistro;
import com.example.glutenfree.entities.Usuario;
import com.example.glutenfree.service.UserService;


@Controller
public class RegistroController {
	  @Autowired
	    private PasswordEncoder passwordEncoder;
	@Autowired
	private UserService servicio;
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
	@GetMapping("/restaurantes_visitados")
	public String verPaginaDeRestaurantes_visitados(Model modelo) {
		return "auth/user/restaurantes_visitados";
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

}
