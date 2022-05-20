package com.ejemplo.security.EjemploSecurity.security.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@NotNull(message = "el nombre no puede ser nulo")
	@Column(name = "nombre")
	private String nombre;
	
	@NotNull(message = "el username no puede ser nulo")
	@Column(name = "nombre_usuario", unique = true)
	private String nombreUsuario;
	
	@NotNull(message = "el email del usuario no puede ser nulo")
	@Column(name = "email")
	private String email;
	
	@NotNull(message = "la clave del usuario no puede ser nula")
	@Column(name = "password")
	private String password;
	
	@ManyToMany
	@JoinTable(name = "usuario_rol", joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id"), 
	inverseJoinColumns = @JoinColumn(name = "rol_id", referencedColumnName = "id"))
	private Set<Rol> roles = new HashSet<>();
	
	
	public Usuario() {
	}


	public Usuario(@NotNull(message = "el nombre no puede ser nulo") String nombre,
			@NotNull(message = "el username no puede ser nulo") String nombreUsuario,
			@NotNull(message = "el email del usuario no puede ser nulo") String email,
			@NotNull(message = "la clave del usuario no puede ser nula") String password) {
		super();
		this.nombre = nombre;
		this.nombreUsuario = nombreUsuario;
		this.email = email;
		this.password = password;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getNombreUsuario() {
		return nombreUsuario;
	}


	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
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

	public Set<Rol> getRoles() {
		return roles;
	}

	public void setRoles(Set<Rol> roles) {
		this.roles = roles;
	}
}
