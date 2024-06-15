package com.example.glutenfree.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.glutenfree.entities.Reseña;
import com.example.glutenfree.entities.Usuario;

@Repository
public interface ReseñaRepository extends JpaRepository<Reseña, Long> {

	List<Reseña> findByUsuario(Usuario user);
    List<Reseña> findByRestauranteNombre(String nombreRestaurante);
}