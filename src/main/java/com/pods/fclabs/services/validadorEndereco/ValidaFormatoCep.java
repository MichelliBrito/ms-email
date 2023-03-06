package com.pods.fclabs.services.validadorEndereco;

import com.pods.fclabs.exception.EnderecoInvalidoException;
import com.pods.fclabs.models.Endereco;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Objects.isNull;

@Component
public class ValidaFormatoCep implements ValidadorEndereco {
    @Override
    public void valida(Endereco endereco) {

        if (isNull(endereco.getCep()) || endereco.getCep().isEmpty()) {
            throw new EnderecoInvalidoException("Campo Cep é obrigatório para cadastro do Endereço");
        }
        Pattern pattern = Pattern.compile("\\d{2}\\.\\d{3}-\\d{3}");
        Matcher matcher = pattern.matcher(endereco.getCep());

        if (!matcher.matches()) {
            throw new EnderecoInvalidoException("Cep no formato invalido");
        }
    }
}
