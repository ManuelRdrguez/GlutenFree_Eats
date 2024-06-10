package com.example.glutenfree.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;


import com.example.glutenfree.dto.UserRegistro;
import com.example.glutenfree.entities.Rol;
import com.example.glutenfree.entities.Usuario;
import com.example.glutenfree.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	private UserRepository userrepository; 

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;	
	public UserServiceImpl(UserRepository userrepository) {
		super();
		this.userrepository = userrepository;
		
	}
	@Override
	public Usuario save(UserRegistro registroDTO) {
		Usuario user = new Usuario(registroDTO.getName(), registroDTO.getApellido(), registroDTO.getEmail(), passwordEncoder.encode(registroDTO.getPassword()), Arrays.asList(new Rol("ROLE_USER"))); 
		return userrepository.save(user); 
	}
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario usuario = userrepository.findByEmail(email);
		if(usuario == null) {
			throw new UsernameNotFoundException("Usuario o password inválidos");
		}
		return new User(usuario.getEmail(),usuario.getPassword(), mapearAutoridadesRoles(usuario.getRoles()));
	}
	private Collection<? extends GrantedAuthority> mapearAutoridadesRoles(Collection<Rol> roles){
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getNombre())).collect(Collectors.toList());
	}
	
	@Override
	public List<Usuario> listarUsuarios() {
		return userrepository.findAll();
	}
	@Override
	public void deleteById(Long id) {
		 userrepository.deleteById(id);
	}


}
