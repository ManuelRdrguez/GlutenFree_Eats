package com.example.glutenfree.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.glutenfree.entities.Reseña;
import com.example.glutenfree.entities.Restaurante;
import com.example.glutenfree.repository.RestauranteRepository;
@Service
public class RestauranteServiceImpl implements RestauranteService {
	 @Autowired
	    private RestauranteRepository restauranteRepository;

	    public List<Restaurante> findAll() {
	        return restauranteRepository.findAll();
	    }

	  

	    public Restaurante save(Restaurante restaurante) {
	        return restauranteRepository.save(restaurante);
	    }

	    public void deleteById(Long id) {
	        restauranteRepository.deleteById(id);
	    }

		public Restaurante findByName(String nombre) {
	        return restauranteRepository.findByNombre(nombre);

		}



		@Override
		public Optional<Restaurante> findById(Long id) {
			return restauranteRepository.findById(id);
		}



		}

	



		

