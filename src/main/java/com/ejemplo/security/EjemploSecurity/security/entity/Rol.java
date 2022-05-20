package com.ejemplo.security.EjemploSecurity.security.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.ejemplo.security.EjemploSecurity.security.enums.RolNombre;

@Entity
@Table(name = "Rol")
public class Rol {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private RolNombre nombreRol;

	public Rol(@NotNull RolNombre nombreRol) {
		this.nombreRol = nombreRol;
	}
	
	public Rol(Integer id, @NotNull RolNombre nombreRol) {
		super();
		this.id = id;
		this.nombreRol = nombreRol;
	}

	public Rol() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public RolNombre getNombreRol() {
		return nombreRol;
	}

	public void setNombreRol(RolNombre nombreRol) {
		this.nombreRol = nombreRol;
	}
}
