package com.example.glutenfree.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.glutenfree.dto.UserRegistro;
import com.example.glutenfree.entities.Usuario;

public interface UserService extends UserDetailsService  {

	public Usuario save(UserRegistro registroDTO);

	List<Usuario> listarUsuarios();

}
