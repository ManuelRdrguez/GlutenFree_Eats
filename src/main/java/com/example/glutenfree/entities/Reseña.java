package com.example.glutenfree.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Reseñas")
public class Reseña {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String comentario;
    private int puntuacion;

    @ManyToOne
    private Restaurante restaurante;

    @ManyToOne
    private Usuario usuario;

    private String nombre_restaurante; 
    // Constructors
    public Reseña() {
    }

    public Reseña(String comentario, int puntuacion, String nombre_restaurante, Usuario usuario) {
        this.comentario = comentario;
        this.puntuacion = puntuacion;
        this.nombre_restaurante = nombre_restaurante;
        this.usuario = usuario;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public String getNombre_restaurante() {
		return nombre_restaurante;
	}

	public void setNombre_restaurante(String nombre_restaurante) {
		this.nombre_restaurante = nombre_restaurante;
	}

	public void setId(Long id) {
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }}