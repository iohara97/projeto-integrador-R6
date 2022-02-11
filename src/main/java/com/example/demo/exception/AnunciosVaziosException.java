package com.example.demo.exception;

public class AnunciosVaziosException extends RuntimeException {

    private static final long serialVersionUID = 616106877599865115L;

    public AnunciosVaziosException(String mensagem) {
        super(mensagem);
    }

}
