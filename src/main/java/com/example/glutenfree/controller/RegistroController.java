package com.example.glutenfree.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.glutenfree.dto.UserRegistro;
import com.example.glutenfree.service.UserService;


@Controller
public class RegistroController {

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
		return "auth/user/ver_perfil";
	}
	@GetMapping("/ver_reseñas")
	public String verPaginaDeVerReseñas(Model modelo) {
		return "auth/user/ver_reseñas";
	}

}
