package com.example.glutenfree.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.glutenfree.entities.Restaurante;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long>  {
    Restaurante findByNombre(String nombre);
    Optional<Restaurante> findById(Long id); 

}
