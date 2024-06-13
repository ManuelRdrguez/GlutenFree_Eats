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
	  public Reseña createReseña(Reseña reseña) {
	        return reseñaRepository.save(reseña);
	    }
	public void deleteById(Long id) {
		reseñaRepository.deleteById(id);		
	}
	@Override
	public List<Reseña> findByUser(Usuario user) {
	    return reseñaRepository.findByUsuario(user);

	}
	
	
	}

