package com.pods.fclabs.services.validadorEndereco;

import com.pods.fclabs.exception.EnderecoInvalidoException;
import com.pods.fclabs.models.Endereco;
import com.pods.fclabs.models.Morada;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidaFormatoCepTest {
    ValidaFormatoCep validador;
    @BeforeEach
    void setUp() {
        validador = new ValidaFormatoCep();
    }

    @Test
    void validaCep() {
        Endereco endereco = Endereco.builder()
                .morada(Morada.AVENIDA)
                .logadouro("Paulo Botti")
                .numero("4000")
                .bairro("Santos Dumont")
                .cidade("Juiz de Fora")
                .build();
        EnderecoInvalidoException ex = assertThrows(EnderecoInvalidoException.class, () -> validador.valida(endereco));

        assertEquals( "Campo Cep é obrigatório para cadastro do Endereço", ex.getMessage());
    }
    @Test
    void validaFormatoCep() {
        Endereco endereco = Endereco.builder()
                .morada(Morada.AVENIDA)
                .logadouro("Paulo Botti")
                .cep("1")
                .numero("4000")
                .bairro("Santos Dumont")
                .cidade("Juiz de Fora")
                .build();
        EnderecoInvalidoException ex = assertThrows(EnderecoInvalidoException.class, () -> validador.valida(endereco));

        assertEquals( "Cep no formato invalido", ex.getMessage());
    }
}