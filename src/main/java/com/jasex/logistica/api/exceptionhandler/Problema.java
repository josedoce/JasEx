package com.jasex.logistica.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
public class Problema {
  private Integer status;
  private OffsetDateTime dataHora;
  private String titulo;
  private List<Campo> campos;

  @Data
  @AllArgsConstructor
  public static class Campo {
    private String nome;
    private String mensagem;
  }
}
