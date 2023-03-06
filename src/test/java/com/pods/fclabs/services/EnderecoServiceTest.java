package com.pods.fclabs.services;

import com.pods.fclabs.exception.EnderecoInvalidoException;
import com.pods.fclabs.models.Endereco;
import com.pods.fclabs.models.Morada;
import com.pods.fclabs.repositories.EnderecoRepository;
import com.pods.fclabs.services.validadorEndereco.ValidadorEndereco;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

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

        ArgumentCaptor<Endereco> argumento = ArgumentCaptor.forClass(Endereco.class);

        service.salvar(endereco);

        Mockito.verify(repository, Mockito.times(1)).save(argumento.capture());
        assertFalse(isNull(argumento.getValue().getDtCriacao()));
        assertFalse(isNull(argumento.getValue().getDtUltAlteracao()));
    }
    @Test
    public void DeveriaDarThrowQuandoAlgumDadoForNulo() {
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
    @Test
    public void DeveriaDarThrowAoCriarEnderecoComId() {
        Endereco endereco = Endereco.builder()
                .id(UUID.randomUUID())
                .morada(Morada.AVENIDA)
                .logadouro("Paulo Botti")
                .numero("4000")
                .bairro("Santos Dumont")
                .cep("36.038-260")
                .cidade("Juiz de Fora")
                .build();

        assertThrows(EnderecoInvalidoException.class,() -> service.salvar(endereco));
        Mockito.verify(repository, Mockito.times(0)).save(endereco);
    }

    @Test
    public void DeveriaAtualizarEnderecoComTodosDados() {
        Endereco endereco = Endereco.builder()
                .id(UUID.randomUUID())
                .morada(Morada.AVENIDA)
                .logadouro("Paulo Botti")
                .numero("4000")
                .bairro("Santos Dumont")
                .cep("36.038-260")
                .cidade("Juiz de Fora")
                .build();

        ArgumentCaptor<Endereco> argumento = ArgumentCaptor.forClass(Endereco.class);

        service.atualiza(endereco);

        Mockito.verify(repository, Mockito.times(1)).save(argumento.capture());
        assertFalse(isNull(argumento.getValue().getDtUltAlteracao()));
    }

    @Test
    public void DeveriaDarThrowAoAtualizarEnderecoSemId() {
        Endereco endereco = Endereco.builder()
                .morada(Morada.AVENIDA)
                .logadouro("Paulo Botti")
                .numero("4000")
                .bairro("Santos Dumont")
                .cep("36.038-260")
                .cidade("Juiz de Fora")
                .build();

        assertThrows(EnderecoInvalidoException.class,() -> service.atualiza(endereco));
        Mockito.verify(repository, Mockito.times(0)).save(endereco);
    }

    @Test
    public void DeveriaDeletarEndereco() {
        UUID id = UUID.randomUUID();
        service.remove(id);
        Mockito.verify(repository, Mockito.times(1)).deleteById(id);
        Mockito.verify(repository, Mockito.times(1)).getById(id);

    }
}