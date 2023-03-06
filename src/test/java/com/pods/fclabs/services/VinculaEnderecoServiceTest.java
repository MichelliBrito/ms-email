package com.pods.fclabs.services;

import com.pods.fclabs.exception.EnderecoInvalidoException;
import com.pods.fclabs.models.Endereco;
import com.pods.fclabs.models.Usuario;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class VinculaEnderecoServiceTest {
    @Mock
    EnderecoService service;
    @InjectMocks
    VinculaEnderecoService vinculador;

    @Test
    void DeveriaVincularEndereco() {
        Usuario usuario = new Usuario();
        Endereco endereco = new Endereco();

        when(service.salvar(any())).thenReturn(endereco);
        Usuario resultado = vinculador.vinculaEndereco(usuario, endereco);

        verify(service, times(1)).salvar(any());
        assertEquals(endereco, resultado.getEndereco());
    }

    @Test
    void DeveriaDarThrowAoSalvarEntidadeQueJaTenhaEndereco() {
        Usuario usuario = new Usuario();
        Endereco endereco = new Endereco();
        usuario.setEndereco(endereco);

        assertThrows(EnderecoInvalidoException.class, () -> vinculador.vinculaEndereco(usuario, endereco));
    }
    @Test
    void DeveriaAtualizarEndereco() {
        Usuario usuario = new Usuario();
        Endereco endereco = new Endereco();
        endereco.setBairro("Bairro1");
        usuario.setEndereco(endereco);
        Endereco endereco2 = new Endereco();
        endereco2.setBairro("Bairro2");


        when(service.atualiza(any())).thenReturn(endereco2);
        Usuario resultado = vinculador.atualizaEndereco(usuario, endereco2);

        verify(service, times(1)).atualiza(any());
        assertEquals(endereco2, resultado.getEndereco());
    }

    @Test
    void DeveriaDarThrowAoAtualizarEntidadeQueSemEndereco() {
        Usuario usuario = new Usuario();
        Endereco endereco = new Endereco();

        assertThrows(EnderecoInvalidoException.class, () -> vinculador.atualizaEndereco(usuario, endereco));
    }

    @Test
    void DeveriaRemoverEndereco() {
        Usuario usuario = new Usuario();
        Endereco endereco = new Endereco();
        usuario.setEndereco(endereco);

        Usuario resultado = vinculador.removeEndereco(usuario);
        verify(service, times(1)).remove(any());


        assertNull(resultado.getEndereco());

    }
}