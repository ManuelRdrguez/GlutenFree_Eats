/**
 * 
 */
package com.example.glutenfree.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;


import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserRegistro implements UserDetails {

    private Long id;
    private String name;
    private String apellido;
    private String numero_telefono; 
    private String email;
    private String password;

    // Constructor con todos los campos
    public UserRegistro(Long id, String name, String apellido, String numero_telefono, String email, String password) {
        this.id = id;
        this.name = name;
        this.apellido = apellido;
        this.numero_telefono = numero_telefono;
        this.email = email;
        this.password = password;
    }

    // Constructor sin ID
    public UserRegistro(String name, String apellido, String numero_telefono, String email, String password) {
        this.name = name;
        this.apellido = apellido;
        this.numero_telefono = numero_telefono;
        this.email = email;
        this.password = password;
    }

    // Constructor solo con email
    public UserRegistro(String email) {
        this.email = email;
    }

    // Constructor por defecto
    public UserRegistro() {
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNumero_telefono() {
        return numero_telefono;
    }

    public void setNumero_telefono(String numero_telefono) {
        this.numero_telefono = numero_telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Implementa esto según tus necesidades, por ahora devolvemos null
        return null;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
