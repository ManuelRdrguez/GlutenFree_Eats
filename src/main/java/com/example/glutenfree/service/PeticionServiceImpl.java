package com.example.glutenfree.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.glutenfree.entities.Peticion;
import com.example.glutenfree.repository.PeticionRepository;
import com.example.glutenfree.repository.ReseñaRepository;
@Service
public class PeticionServiceImpl implements PeticionService {
	 @Autowired
	    private PeticionRepository peticionservice;
	@Override
	public List<Peticion> findAll() {
		return peticionservice.findAll();
	}
	@Override
	public Peticion save(Peticion peticion) {
		return peticionservice.save(peticion); 
	}
	@Override
	public void deleteById(Long id) {
		 peticionservice.deleteById(id);
		
	}

}
