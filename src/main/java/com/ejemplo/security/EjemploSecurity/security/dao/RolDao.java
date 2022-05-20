package com.ejemplo.security.EjemploSecurity.security.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ejemplo.security.EjemploSecurity.security.entity.Rol;
import com.ejemplo.security.EjemploSecurity.security.enums.RolNombre;

@Repository
public interface RolDao extends JpaRepository<Rol, Integer> {

	Optional<Rol> findByNombreRol(RolNombre nombreRol);
}
