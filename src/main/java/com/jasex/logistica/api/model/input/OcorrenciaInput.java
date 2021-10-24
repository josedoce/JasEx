package com.jasex.logistica.api.model.input;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class OcorrenciaInput {
  @NotBlank
  private String descricao;
}
