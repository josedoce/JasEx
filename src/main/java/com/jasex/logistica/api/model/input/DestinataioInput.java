package com.jasex.logistica.api.model.input;

import javax.validation.constraints.NotBlank;

import lombok.Data;
@Data
public class DestinataioInput {
  @NotBlank
  private String nome;
  @NotBlank
  private String logradouro;
  @NotBlank
  private String numero;
  @NotBlank
  private String complemento;
  @NotBlank
  private String bairro;
}
