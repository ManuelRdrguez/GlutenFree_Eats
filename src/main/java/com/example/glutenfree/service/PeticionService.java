package com.example.glutenfree.service;

import java.util.List;

import com.example.glutenfree.entities.Peticion;

public interface PeticionService {
	public List<Peticion> findAll(); 
	public Peticion save(Peticion peticion); 
	public void deleteById(Long id);
}
