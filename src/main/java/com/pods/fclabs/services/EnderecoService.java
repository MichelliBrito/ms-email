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

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository repository;
    @Autowired
    private List<ValidadorEndereco> validadores;

    public Endereco salvar(Endereco endereco) {
        try {
            if(nonNull(endereco.getId())) {
                throw new EnderecoInvalidoException("Novo endereço não pode ter id");
            }
            validadores.forEach(validador -> validador.valida(endereco));
            endereco.setId(UUID.randomUUID());
            endereco.setDtCriacao(Util.formatarData(new Date()));
            endereco.setDtUltAlteracao(Util.formatarData(new Date()));
            return repository.save(endereco);
        } catch (EnderecoInvalidoException e) {
            throw e;
        }
    }
    public Endereco atualiza(Endereco endereco) {
        try {
            if(isNull(endereco.getId())) {
                throw new EnderecoInvalidoException("É preciso id válido para atualizar endereço");
            }
            validadores.forEach(validador -> validador.valida(endereco));
            endereco.setDtUltAlteracao(Util.formatarData(new Date()));
            return repository.save(endereco);
        } catch (EnderecoInvalidoException e) {
            throw e;
        }
    }

    public Endereco remove(UUID id) {
        repository.deleteById(id);
        return repository.getById(id);
    }
}
