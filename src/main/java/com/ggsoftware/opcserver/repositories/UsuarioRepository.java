package com.ggsoftware.opcserver.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ggsoftware.opcserver.entity.UsuarioModel;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, Integer> {
	
	public Optional<UsuarioModel> findByLogin(String login);

}
