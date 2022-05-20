package com.ejemplo.security.EjemploSecurity.security.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ejemplo.security.EjemploSecurity.security.dao.UsuarioDao;
import com.ejemplo.security.EjemploSecurity.security.entity.Usuario;

@Service
@Transactional
public class UsuarioService {

	@Autowired
	UsuarioDao usuarioDao;
	
	
	public Optional<Usuario> getByNombreUsuario(String nombreUsuario) {
		return usuarioDao.findByNombreUsuario(nombreUsuario);
	}
	
	public boolean existsByNombreUsuario(String nombreUsuario) {
		return usuarioDao.existsByNombreUsuario(nombreUsuario);
	}
	
	public boolean existsByEmail(String email) {
		return usuarioDao.existsByEmail(email);
	}
	
	public void saveUser(Usuario usuario) {
		this.usuarioDao.save(usuario);
	}
}
