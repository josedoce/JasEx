package com.jasex.logistica.api.exceptionhandler;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
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

/*
  Essa anotação diz que é um componente do spring
  mas com um proposito especifico de tratar excessões
  de forma global(para todos os controladores da aplicação.). 

  A classe ResponseEntityExceptionHandler é a base, então,
  iremos extender ela...
*/
@ControllerAdvice
@AllArgsConstructor
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
  //se acessar sem alterár nada, nós não receberemos nenhum corpo de erro no frontend.

  //isso configura o arquivo de mensagens de erro customizadas
  private MessageSource messagesource;

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
      HttpStatus status, WebRequest request) {
    List<Problema.Campo> campos = new ArrayList<>();
    String titulo = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.";
    for(ObjectError error : ex.getBindingResult().getAllErrors()) {
      String nome = ((FieldError) error).getField();
      //pegando a mensagem customizada no properties
      String mensagem = messagesource.getMessage(error, LocaleContextHolder.getLocale());
      campos.add(new Problema.Campo(nome, mensagem));
    }

    //agora vamos traduzir mensagens no messages.properties
    Problema problema = new Problema(status.value(), OffsetDateTime.now(), titulo, campos);
    
    return handleExceptionInternal(ex, problema, headers, status, request);
  }

  //essa anotação diz: Trate todo erro que for originario de NegocioException
  //que seja lançado em qualquer parte da aplicação.
  @ExceptionHandler(NegocioException.class)
  public ResponseEntity<Object> handleNegocio(NegocioException ex, WebRequest request){ //o spring passa a request...
    HttpStatus status = HttpStatus.BAD_REQUEST;
    Problema problema = new Problema(status.value(), OffsetDateTime.now(), ex.getMessage(), null);
    //se quiser, pode customizar o HttpHeaders...
    return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
  }

  @ExceptionHandler(EntidadeNaoEncontradaException.class)
  public ResponseEntity<Object> handleEntidadeNaoEncontrada(EntidadeNaoEncontradaException ex, WebRequest request){ //o spring passa a request...
    HttpStatus status = HttpStatus.NOT_FOUND;
    Problema problema = new Problema(status.value(), OffsetDateTime.now(), ex.getMessage(), null);
    //se quiser, pode customizar o HttpHeaders...
    return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
  }
}
