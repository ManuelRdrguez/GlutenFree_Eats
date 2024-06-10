package com.example.glutenfree.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.glutenfree.entities.Usuario;

@Repository
public interface UserRepository extends JpaRepository<Usuario, Long> {
    @Query("SELECT u FROM Usuario u WHERE u.email = ?1")
	public Usuario findByEmail(String email);

}
