package com.example.glutenfree.entities;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Rol")
public class Rol {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nombre;

	// CONSTRUCTORS

	public Rol(Long id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}

	public Rol() {
		super();
	}
	// GETTERS ANDS SETTERS

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Rol(String nombre) { 	
		super();
		this.nombre = nombre;
	}

}
