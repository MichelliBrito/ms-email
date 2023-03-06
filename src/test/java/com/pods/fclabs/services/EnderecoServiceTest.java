package com.pods.fclabs.services;

import com.pods.fclabs.exception.EnderecoInvalidoException;
import com.pods.fclabs.models.Endereco;
import com.pods.fclabs.models.Morada;
import com.pods.fclabs.repositories.EnderecoRepository;
import com.pods.fclabs.services.validadorEndereco.ValidadorEndereco;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.Objects.isNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class EnderecoServiceTest {
    @Mock
    EnderecoRepository repository;
    @Mock
    List<ValidadorEndereco> validadores = Collections.singletonList(mock(ValidadorEndereco.class));
    @InjectMocks
    EnderecoService service;

    @Test
    public void DeveriaSalvarNovoEnderecoComTodosDados() {
        Endereco endereco = Endereco.builder()
                .morada(Morada.AVENIDA)
                .logadouro("Paulo Botti")
                .numero("4000")
                .bairro("Santos Dumont")
                .cep("36.038-260")
                .cidade("Juiz de Fora")
                .build();

        Endereco resultado = service.salvar(endereco);

        Mockito.verify(repository, Mockito.times(1)).save(endereco);
        assertFalse(isNull(resultado.getDtCriacao()));
        assertFalse(isNull(resultado.getDtUltAlteracao()));
    }
    @Test
    public void DeveriaDarThrowAoSalvarQuandoAlgumDadoForNulo() {
        Endereco endereco = Endereco.builder()
                .morada(Morada.AVENIDA)
                .logadouro("Paulo Botti")
                .numero("4000")
                .bairro("Santos Dumont")
                .cep("36.038-260")
                .cidade("Juiz de Fora")
                .build();

        doThrow(EnderecoInvalidoException.class).when(validadores).forEach(any());
        assertThrows(EnderecoInvalidoException.class,() -> service.salvar(endereco));
        Mockito.verify(repository, Mockito.times(0)).save(endereco);
    }
}