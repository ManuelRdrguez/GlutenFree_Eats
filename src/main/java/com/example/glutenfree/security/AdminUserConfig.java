package com.example.glutenfree.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.glutenfree.entities.Rol;
import com.example.glutenfree.entities.Usuario;
import com.example.glutenfree.repository.UserRepository;

@Configuration
public class AdminUserConfig {
	
	    @Autowired
	    private BCryptPasswordEncoder passwordEncoder;

	    @Autowired
	    private UserRepository userRepository;

	    @Bean
	    public CommandLineRunner insertAdminUser() {
	        return args -> {
	            if (userRepository.findByEmail("admin@example.com") == null) {
	                Usuario admin = new Usuario(
	                    "Admin",
	                    "Admin",
	                    "admin@example.com",
	                    passwordEncoder.encode("admin123"),
	                    Arrays.asList(new Rol("ROLE_ADMIN"))
	                );
	                userRepository.save(admin);
	            }
	        };
	    }
	}

