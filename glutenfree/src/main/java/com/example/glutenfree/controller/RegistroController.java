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
	@GetMapping("/")
	public String verPaginaDeInicio(Model modelo) {
		modelo.addAttribute("usuarios", servicio.listarUsuarios());
		return "index";
	}

}
