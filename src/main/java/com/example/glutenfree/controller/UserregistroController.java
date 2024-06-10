package com.example.glutenfree.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.glutenfree.dto.UserRegistro;
import com.example.glutenfree.service.UserService;

@Controller
@RequestMapping("/registro")
public class UserregistroController {
	
	private UserService userservice;

	public UserregistroController(UserService usuarioServicio) {
		super();
		this.userservice = usuarioServicio;
	}
	@ModelAttribute("user")
	public UserRegistro devolverNuevoUserRegistroDto() {
		return new UserRegistro(); 
		}
	@GetMapping
	public String mostrarFormulariodeRegistro() {
		return "registro"; 
		
	}
	@PostMapping
	public String registrarCuentaUser(@ModelAttribute("Usuario") UserRegistro registro) {
		userservice.save(registro); 
		return "redirect:/registro?exito"; 
	}
}
