package com.example.glutenfree.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.glutenfree.entities.Reseña;

@Repository
public interface ReseñaRepository extends JpaRepository<Reseña, Long> {
}