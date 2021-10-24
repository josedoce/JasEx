package com.jasex.logistica.api.model.input;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class EntregaInput {
  @Valid 
  @NotNull
  private ClienteIdInput cliente;

  @Valid
  @NotNull
  private DestinataioInput destinatario;

  @NotNull
  private BigDecimal taxa;
}
