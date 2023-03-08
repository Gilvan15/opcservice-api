package com.ggsoftware.opcserver.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ggsoftware.opcserver.data.DetalheUsuarioData;
import com.ggsoftware.opcserver.entity.UsuarioModel;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class JWTAutenticarFilter extends UsernamePasswordAuthenticationFilter {
	
	public static final int TOKEN_EXPIRACAO = (60 * 60 * 1000); // 1h
	public static final String TOKEN_SENHA = "0e5d12b2-6d7c-443b-ab90-4b2c56cfeb3f";
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
												HttpServletResponse response)throws AuthenticationServiceException {
		try {
			UsuarioModel usuario = new  ObjectMapper()
					.readValue(request.getInputStream(), UsuarioModel.class);
			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					usuario.getLogin(), 
					usuario.getPassword(),
					new ArrayList<>()
					)); 
		} catch (IOException e) {
			throw new RuntimeException("Falha ao autenticar o usuário", e);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, 
			HttpServletResponse response, 
			FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		DetalheUsuarioData usuarioData = (DetalheUsuarioData) authResult.getPrincipal();
		
		String token = JWT.create()
				.withSubject(usuarioData.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis()+ TOKEN_EXPIRACAO ))
				.sign(Algorithm.HMAC512(TOKEN_SENHA));
	
		response.getWriter().write(token);
		response.getWriter().flush();
 
	}
	
	

}
