package com.example.glutenfree.service;

import java.util.List;

import com.example.glutenfree.entities.Reseña;
import com.example.glutenfree.entities.Restaurante;
import com.example.glutenfree.entities.Usuario;

public interface ReseñaService {
    public List<Reseña> findAll() ;
    public List<Reseña> findByUser(Usuario user);
    public Reseña createReseña(Reseña reseña);
    public void deleteById(Long id);
	List<Reseña> encontrarReseñasPorNombreRestaurante(String nombreRestaurante); 


	

}
