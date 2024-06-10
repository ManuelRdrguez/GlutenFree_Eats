package com.example.glutenfree.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.glutenfree.dto.UserRegistro;
import com.example.glutenfree.entities.Usuario;
import com.example.glutenfree.service.UserService;

@Controller
public class UsuarioControler {
	private UserService userservice;

	public UsuarioControler(UserService usuarioServicio) {
		super();
		this.userservice = usuarioServicio;
	}

	@GetMapping("/crearUsuario")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
	public String mostrarFormularioCreacionUsuario() {
		return "auth/admin/crearusuario"; // nombre del archivo HTML (crearUsuario.html)
	}

	@ModelAttribute("user")
	public UserRegistro devolverNuevoUserRegistroDto() {
		return new UserRegistro();
	}

	@PostMapping("/crearUsuario")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
	public String registrarCuentaUser(@ModelAttribute("Usuario") UserRegistro registro, Model model) {
		userservice.save(registro);
		List<Usuario> usuarios = userservice.listarUsuarios();
		model.addAttribute("usuarios", usuarios);
		return "auth/admin/usuarios";
	}

	@GetMapping("/eliminarUsuario/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")

	public String eliminarUsuario(@PathVariable Long id, Model model) {
		userservice.deleteById(id);
		List<Usuario> usuarios = userservice.listarUsuarios();
		model.addAttribute("usuarios", usuarios);
		return "auth/admin/usuarios";
	}
}
