package com.example.glutenfree.service;

import java.util.List;

import com.example.glutenfree.entities.Reseña;
import com.example.glutenfree.entities.Restaurante;

public interface ReseñaService {
    public List<Reseña> findAll() ;

	public Reseña crearReseña(String comentario, int puntuacion,  String nombre_restaurante, String username);





	

}
