package com.jasex.logistica.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import com.jasex.logistica.api.exception.EntidadeNaoEncontradaException;
import com.jasex.logistica.api.exception.NegocioException;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.AllArgsConstructor;

@ControllerAdvice
@AllArgsConstructor
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
  private MessageSource messagesource;

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
      HttpStatus status, WebRequest request) {
    List<Problema.Campo> campos = new ArrayList<>();
    String titulo = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.";
    for(ObjectError error : ex.getBindingResult().getAllErrors()) {
      String nome = ((FieldError) error).getField();
      String mensagem = messagesource.getMessage(error, LocaleContextHolder.getLocale());
      campos.add(new Problema.Campo(nome, mensagem));
    }

    Problema problema = new Problema(status.value(), OffsetDateTime.now(), titulo, campos);
    
    return handleExceptionInternal(ex, problema, headers, status, request);
  }

  @ExceptionHandler(NegocioException.class)
  public ResponseEntity<Object> handleNegocio(NegocioException ex, WebRequest request){
    HttpStatus status = HttpStatus.BAD_REQUEST;
    Problema problema = new Problema(status.value(), OffsetDateTime.now(), ex.getMessage(), null);
    return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
  }

  @ExceptionHandler(EntidadeNaoEncontradaException.class)
  public ResponseEntity<Object> handleEntidadeNaoEncontrada(EntidadeNaoEncontradaException ex, WebRequest request){ //o spring passa a request...
    HttpStatus status = HttpStatus.NOT_FOUND;
    Problema problema = new Problema(status.value(), OffsetDateTime.now(), ex.getMessage(), null);
    return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
  }
}
