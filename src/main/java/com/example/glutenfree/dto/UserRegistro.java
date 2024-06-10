/**
 * 
 */
package com.example.glutenfree.dto;

import jakarta.persistence.Column;

public class UserRegistro {

	private Long id;
	private String name;
	private String apellido;
	private String email;
	private String password;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public UserRegistro(Long id, String name, String apellido, String email, String password) {
		super();
		this.id = id;
		this.name = name;
		this.apellido = apellido;
		this.email = email;
		this.password = password;
	}

	public UserRegistro(String name, String apellido, String email, String password) {
		super();
		this.name = name;
		this.apellido = apellido;
		this.email = email;
		this.password = password;
	}

	public UserRegistro(String email) {
		super();
		this.email = email;
	}

	public UserRegistro() {
		super();
	}

}
