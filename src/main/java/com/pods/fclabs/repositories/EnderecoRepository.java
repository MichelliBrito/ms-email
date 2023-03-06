package com.pods.fclabs.repositories;

import com.pods.fclabs.models.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EnderecoRepository extends JpaRepository<Endereco, UUID> {
}
