package com.pods.fclabs.services.validadorEndereco;

import com.pods.fclabs.exception.EnderecoInvalidoException;
import com.pods.fclabs.models.Endereco;
import com.pods.fclabs.models.Morada;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidaCamposNulosTest {
    ValidaCamposNulos validador;
    @BeforeEach
    void setUp() {
        validador = new ValidaCamposNulos();
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
    void validaLogadouro() {
        Endereco endereco = Endereco.builder()
                .morada(Morada.AVENIDA)
                .numero("4000")
                .bairro("Santos Dumont")
                .cep("36.038-260")
                .cidade("Juiz de Fora")
                .build();
        EnderecoInvalidoException ex = assertThrows(EnderecoInvalidoException.class, () -> validador.valida(endereco));

        assertEquals( "Campo Logadouro é obrigatório para cadastro do Endereço", ex.getMessage());
    }

    @Test
    void validaMorada() {
        Endereco endereco = Endereco.builder()
                .morada(Morada.AVENIDA)
                .logadouro("Paulo Botti")
                .bairro("Santos Dumont")
                .cep("36.038-260")
                .cidade("Juiz de Fora")
                .build();
        EnderecoInvalidoException ex = assertThrows(EnderecoInvalidoException.class, () -> validador.valida(endereco));

        assertEquals( "Campo Numero é obrigatório para cadastro do Endereço", ex.getMessage());
    }

    @Test
    void validaNumero() {
        Endereco endereco = Endereco.builder()
                .morada(Morada.AVENIDA)
                .logadouro("Paulo Botti")
                .numero("4000")
                .cep("36.038-260")
                .cidade("Juiz de Fora")
                .build();
        EnderecoInvalidoException ex = assertThrows(EnderecoInvalidoException.class, () -> validador.valida(endereco));

        assertEquals( "Campo Bairro é obrigatório para cadastro do Endereço", ex.getMessage());
    }

    @Test
    void validaBairro() {
        Endereco endereco = Endereco.builder()
                .morada(Morada.AVENIDA)
                .logadouro("Paulo Botti")
                .numero("4000")
                .bairro("Santos Dumont")
                .cep("36.038-260")
                .build();
        EnderecoInvalidoException ex = assertThrows(EnderecoInvalidoException.class, () -> validador.valida(endereco));

        assertEquals( "Campo Cidade é obrigatório para cadastro do Endereço", ex.getMessage());
    }

    @Test
    void validaCidade() {
        Endereco endereco = Endereco.builder()
                .logadouro("Paulo Botti")
                .numero("4000")
                .bairro("Santos Dumont")
                .cep("36.038-260")
                .cidade("Juiz de Fora")
                .build();
        EnderecoInvalidoException ex = assertThrows(EnderecoInvalidoException.class, () -> validador.valida(endereco));

        assertEquals( "Campo Morada é obrigatório para cadastro do Endereço", ex.getMessage());
    }
}