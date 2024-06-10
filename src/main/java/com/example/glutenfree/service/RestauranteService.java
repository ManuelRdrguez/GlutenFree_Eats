package com.example.glutenfree.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.glutenfree.dto.UserRegistro;
import com.example.glutenfree.entities.Restaurante;
import com.example.glutenfree.entities.Usuario;
import com.example.glutenfree.repository.RestauranteRepository;


public interface RestauranteService {

	 public Restaurante findByName(String nombre); 
    public List<Restaurante> findAll() ;

    public Optional<Restaurante> findById(Long id);

    public Restaurante save(Restaurante restaurante) ;

    public void deleteById(Long id);
}



