package com.example.glutenfree.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.glutenfree.entities.Reseña;
import com.example.glutenfree.entities.Restaurante;
import com.example.glutenfree.entities.Usuario;
import com.example.glutenfree.repository.ReseñaRepository;
import com.example.glutenfree.repository.UserRepository;

@Service
public class ReseñaServiceImpl implements ReseñaService{
	   @Autowired
	    private ReseñaRepository reseñaRepository;

	    @Autowired
	    private UserRepository usuarioRepository;
	@Override
	public List<Reseña> findAll() {
        return reseñaRepository.findAll();

	}
	@Override
	public Reseña crearReseña(String comentario, int puntuacion,  String nombrerestaurante, String username) {
		  Usuario usuario = usuarioRepository.findByEmail(username);
	        if (usuario == null) {
	            throw new IllegalArgumentException("Usuario no encontrado");
	        }
	        Reseña reseña = new Reseña(comentario, puntuacion, nombrerestaurante, usuario);
	        return reseñaRepository.save(reseña);
	    }		
	}

