package com.pods.fclabs.exception;

public class AzureSearchException extends RuntimeException {
    public AzureSearchException(String message, Exception exception) {
        super(message, exception);
    }
}
