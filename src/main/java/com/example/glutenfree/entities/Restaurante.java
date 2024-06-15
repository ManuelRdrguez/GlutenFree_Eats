package com.example.glutenfree.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Restaurantes")
public class Restaurante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    private String descripcion;
    private String direccion;
    private String horario;
    private String telefono;
    private String opciones_celiacos;
    @OneToMany(mappedBy = "restaurante", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Reseña> reseñas;

    // Constructors
    public Restaurante() {
    }

    public Restaurante(String nombre, String descripcion, String direccion, String horario, String telefono) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.direccion = direccion;
        this.horario = horario;
        this.telefono = telefono;
    }

    public Restaurante(Long id, String nombre, String descripcion, String direccion, String horario, String telefono,
			String opciones_celiacos, List<Reseña> reseñas) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.direccion = direccion;
		this.horario = horario;
		this.telefono = telefono;
		this.opciones_celiacos = opciones_celiacos;
		this.reseñas = reseñas;
	}

	// Getters and Setters
    
    
    public Long getId() {
        return id;
    }

    public String getOpciones_celiacos() {
		return opciones_celiacos;
	}

	public void setOpciones_celiacos(String opciones_celiacos) {
		this.opciones_celiacos = opciones_celiacos;
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

	public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public List<Reseña> getReseñas() {
        return reseñas;
    }

    public void setReseñas(List<Reseña> reseñas) {
        this.reseñas = reseñas;
    }
}
