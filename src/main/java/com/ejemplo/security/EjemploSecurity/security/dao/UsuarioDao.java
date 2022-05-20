package com.ejemplo.security.EjemploSecurity.security.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ejemplo.security.EjemploSecurity.security.entity.Usuario;

@Repository
public interface UsuarioDao extends JpaRepository<Usuario, Integer> {

	Optional<Usuario> findByNombreUsuario(String nombreUsuario);
	
	boolean existsByNombreUsuario(String nombreUsuario);
	
	boolean existsByEmail(String nombreUsuario);
}
