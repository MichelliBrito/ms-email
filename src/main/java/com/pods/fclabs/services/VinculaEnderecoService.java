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

    public <T extends EntidadeComEnderecoEAuditoriaAbstrata> T vinculaEndereco(T entidade, Endereco endereco) {
        try {
            if (nonNull(entidade.getEndereco()))
                throw new EnderecoInvalidoException("Não é possivel vincular endereço de entidade com endereço");

            Endereco resultado = enderecoService.salvar(endereco);

            entidade.setEndereco(resultado);
            entidade.setDtUltAlteracao(Util.formatarData(new Date()));
            return entidade;
        } catch (EnderecoInvalidoException e) {
            throw e;
        }
    }

    public <T extends EntidadeComEnderecoEAuditoriaAbstrata> T atualizaEndereco(T entidade, Endereco endereco) {
        try {
            if (isNull(entidade.getEndereco()))
                throw new EnderecoInvalidoException("Não é possivel atualizar endereço de entidade sem endereço");
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
        enderecoService.remove(entidade.getEndereco().getId());
        entidade.setEndereco(null);
        entidade.setDtUltAlteracao(Util.formatarData(new Date()));
        return entidade;
    }
}
