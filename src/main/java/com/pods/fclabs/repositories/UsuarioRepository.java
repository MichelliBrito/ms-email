package com.pods.fclabs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pods.fclabs.models.Usuario;

import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
	
}
