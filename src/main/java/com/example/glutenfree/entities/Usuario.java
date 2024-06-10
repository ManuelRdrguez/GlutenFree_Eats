package com.example.glutenfree.entities;

import java.util.Collection;

import org.hibernate.annotations.ManyToAny;

import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "Usuarios", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	
	@Column(name = "nombre")
	private String username; 
	@Column(name = "apellido")
	private String apellido; 
	private String email;
	private String password; 
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "usuarios_roles", joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "rol_id", referencedColumnName = "id"))
	private Collection<Rol> roles;
	
	
	//CONSTRUCTORS 
	public Usuario(Long id, String username, String apellido, String email, String password, Collection<Rol> roles) {
		super();
		this.id = id;
		this.username = username;
		this.apellido = apellido;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}
	
	public Usuario() {
		super();
	}



	public Usuario(String name, String apellido, String email, String password, Collection<Rol> roles) {
		super();
		this.username = name;
		this.apellido = apellido;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}

	//GETTERS AND SETTERS 
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Collection<Rol> getRoles() {
		return roles;
	}
	public void setRoles(Collection<Rol> roles) {
		this.roles = roles;
	}

	
	
}
