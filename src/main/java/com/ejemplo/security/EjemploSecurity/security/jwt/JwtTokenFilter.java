package com.ejemplo.security.EjemploSecurity.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ejemplo.security.EjemploSecurity.security.service.UserDetailsServiceImpl;

public class JwtTokenFilter extends OncePerRequestFilter{

	@Autowired
	JwtProvider jwtProvider;
	
	@Autowired
	UserDetailsServiceImpl userDetailsServiceImpl;
	
	
	//Este método se ejecuta una vez cada consumo de api para validar token
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {		
		
		try {
			String token =  getToken(request);
			
			if(token != null && jwtProvider.validateToken(token)) {
				String nombreUsuario = jwtProvider.getNombreUsuarioFromToken(token);
				UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(nombreUsuario);
				UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, 
						userDetails.getAuthorities());
				
				// Setea la sesion para posterior uso (user authenticated)
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		} 
		catch (Exception e) {
			logger.error("Falló el metodo doFilterInternal");
		}
		filterChain.doFilter(request, response);
	}
	
	
	public String getToken(HttpServletRequest request) {
		String header = request.getHeader("Authorization");
		
		if(header != null && header.startsWith("Bearer")) {
			return header.replace("Bearer ", "");
		}
		
		return null;
	}

}
