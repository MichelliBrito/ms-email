package com.pods.fclabs.services;

import com.pods.fclabs.exception.EnderecoInvalidoException;
import com.pods.fclabs.models.Endereco;
import com.pods.fclabs.models.EntidadeComEnderecoEAuditoriaAbstrata;
import com.pods.fclabs.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
public class VinculaEnderecoService {
    @Autowired
    EnderecoService enderecoService;

    public <T extends EntidadeComEnderecoEAuditoriaAbstrata> T vinculaNovoEndereco(T entidade, Endereco endereco) {
        try {
            if (nonNull(endereco.getId()))
                throw new EnderecoInvalidoException("Não é possivel vincular endereço que já exista");

            Endereco resultado = enderecoService.salva(endereco);

            entidade.setEndereco(resultado);
            entidade.setDtUltAlteracao(Util.formatarData(new Date()));
            return entidade;
        } catch (EnderecoInvalidoException e) {
            throw e;
        }
    }

    public <T extends EntidadeComEnderecoEAuditoriaAbstrata> T atualizaEndereco(T entidade, Endereco endereco) {
        try {
            if (isNull(endereco.getId()))
                throw new EnderecoInvalidoException("Não é possivel atualizar endereço que não exista");
            endereco.setId(entidade.getEndereco().getId());

            Endereco resultado = enderecoService.atualiza(endereco);

            entidade.setEndereco(resultado);
            entidade.setDtUltAlteracao(Util.formatarData(new Date()));
            return entidade;
        } catch (EnderecoInvalidoException e) {
            throw e;
        }
    }

    public <T extends EntidadeComEnderecoEAuditoriaAbstrata> T removeEndereco(T entidade) {
        if(entidade.getEndereco() == null)
            return entidade;

        enderecoService.remove(entidade.getEndereco().getId());
        entidade.setEndereco(null);
        entidade.setDtUltAlteracao(Util.formatarData(new Date()));
        return entidade;
    }
    public <T extends EntidadeComEnderecoEAuditoriaAbstrata> T ajustaEndereco(T entidade) {
        if(isNull(entidade.getEndereco())) {
            return removeEndereco(entidade);
        }
        if(isNull(entidade.getId()) || isNull(entidade.getEndereco().getId())) {
            return vinculaNovoEndereco(entidade, entidade.getEndereco());
        }
        return atualizaEndereco(entidade, entidade.getEndereco());
    }
}
