package com.ejemplo.security.EjemploSecurity.security.jwt;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.ejemplo.security.EjemploSecurity.security.entity.UsuarioPrincipal;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtProvider {

	private final static Logger logger = LoggerFactory.getLogger(JwtProvider.class);
	
	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${jwt.expiration}")
	private int expiration;
	
	
	public String generateToken(Authentication authentication) {
		UsuarioPrincipal usuarioPrincipal = (UsuarioPrincipal) authentication.getPrincipal();
		
		//Construye el token
		return Jwts.builder().setSubject(usuarioPrincipal.getNombreUsuario())
				.setIssuedAt(new Date())
				.setExpiration(new Date(new Date().getTime() + expiration * 1000))
				.signWith(SignatureAlgorithm.HS512, secret)
				.compact();
	}
	
	//Obtiene el nombre a partir de dicho token
	public String getNombreUsuarioFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
	}
	
	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
			return true;
		} 
		catch (MalformedJwtException e) {
			logger.error("El token está mal formado o generado");
		}
		catch (UnsupportedJwtException e) {
			logger.error("El token no está soportado");
		}
		catch (ExpiredJwtException e) {
			logger.error("El token ha expirado");
		}
		catch (IllegalArgumentException e) {
			logger.error("El token está vacio");
		}
		catch (SignatureException e) {
			logger.error("El token está mal firmado");
		}
		
		return false;
	}
	
}
