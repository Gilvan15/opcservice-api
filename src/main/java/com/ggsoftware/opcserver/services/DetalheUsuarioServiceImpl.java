package com.ggsoftware.opcserver.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.ggsoftware.opcserver.data.DetalheUsuarioData;
import com.ggsoftware.opcserver.entity.UsuarioModel;
import com.ggsoftware.opcserver.repositories.UsuarioRepository;

@Component
public class DetalheUsuarioServiceImpl implements UserDetailsService {
	
	@Autowired
	UsuarioRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<UsuarioModel> usuario = repository.findByLogin(username);
		if(usuario.isEmpty()) {
			throw new UsernameNotFoundException(" Usuário [" + username + "] não encontrado!  ");
		}
 
		return new DetalheUsuarioData(usuario);
	}

}
