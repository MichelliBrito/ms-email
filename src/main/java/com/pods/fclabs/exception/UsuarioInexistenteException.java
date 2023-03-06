package com.pods.fclabs.exception;

public class UsuarioInexistenteException extends RuntimeException {
    public UsuarioInexistenteException() {
        super();
    }

    public UsuarioInexistenteException(String mensagem) {
        super(mensagem);
    }
}
