package com.pods.fclabs.exception;

public class UsuarioExistenteException extends RuntimeException {
    public UsuarioExistenteException(String mensagem) {
        super(mensagem);
    }
}
