package com.example.demo.controller;

import com.example.demo.exception.ProdutoVazioException;
import exception.BusinessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import com.example.demo.exception.AnunciosVaziosException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<Object> handleBusinessException(BusinessException ex, WebRequest request) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<Object> handleRuntimeException(RuntimeException ex, WebRequest request) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    /**
     * O handler usado para tratar as validações nos DTOs
     * @param ex (MethodArgumentNotValidException)
     * @return HttpResponse 400 com a mensagem de erro
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    /**
     * O handler usado para tratar erro na validação dos valores do Body Request
     * @param ex (HttpMessageNotReadableException)
     * @param request (WebRequest)
     * @return HTTPResponse 400 com a mensagem de erro (String)
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<Object> handleHTTPMessageNotReadableExceptions(HttpMessageNotReadableException ex, WebRequest request) {
        return ResponseEntity.badRequest().body("Houve erro ao tentar serializar um campo.");
    }


    /**
     * O handler usado para tratar a listagem de anúncios vazia.
     * @param ex (AnunciosVaziosException)
     * @param request (WebRequest)
     * @return HTTPResponse 404 com a mensagem de erro (String)
     */
    @ExceptionHandler(AnunciosVaziosException.class)
    protected ResponseEntity<Object> handleAnunciosVaziosException(AnunciosVaziosException ex, WebRequest request) {
        return ResponseEntity.notFound().build();
    }

    /**
     * O handler usado para tratar a listagem de armazem de produto vazio.
     * @param ex (ProdutoVazioException)
     * @param request (WebRequest)
     * @return HTTPResponse 404 com a mensagem de erro (String)
     */
    @ExceptionHandler(ProdutoVazioException.class)
    protected ResponseEntity<Object> handleProdutoVazioException(ProdutoVazioException ex, WebRequest request) {
        return ResponseEntity.notFound().build();
    }

}
