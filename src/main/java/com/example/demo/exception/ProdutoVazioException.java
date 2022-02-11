package com.example.demo.exception;

public class ProdutoVazioException extends RuntimeException {

    private static final long serialVersionUID = 3642759996241792935L;

    public ProdutoVazioException(String mensagem) {
        super(mensagem);
    }

}
