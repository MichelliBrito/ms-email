package com.pods.fclabs.services;

import com.pods.fclabs.exception.EnderecoInvalidoException;
import com.pods.fclabs.models.Endereco;
import com.pods.fclabs.repositories.EnderecoRepository;
import com.pods.fclabs.services.validadorEndereco.ValidadorEndereco;
import com.pods.fclabs.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository repository;
    @Autowired
    private List<ValidadorEndereco> validadores;

    public Endereco salvar(Endereco endereco) {
        try {
            validadores.forEach(validador -> validador.valida(endereco));
            endereco.setId(UUID.randomUUID());
            endereco.setDtCriacao(Util.formatarData(new Date()));
            endereco.setDtUltAlteracao(Util.formatarData(new Date()));
            return repository.save(endereco);
        } catch (EnderecoInvalidoException e) {
            throw e;
        }
    }
}
