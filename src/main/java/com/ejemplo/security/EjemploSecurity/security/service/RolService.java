package com.ejemplo.security.EjemploSecurity.security.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ejemplo.security.EjemploSecurity.security.dao.RolDao;
import com.ejemplo.security.EjemploSecurity.security.entity.Rol;
import com.ejemplo.security.EjemploSecurity.security.enums.RolNombre;

@Service
@Transactional
public class RolService {

	@Autowired
	RolDao rolDao;
	
	
	public Optional<Rol> getByNombreRol(RolNombre nombreRol) {
		return rolDao.findByNombreRol(nombreRol);
	}
	
	public void save(Rol rol) {
		rolDao.save(rol);
	}
}
