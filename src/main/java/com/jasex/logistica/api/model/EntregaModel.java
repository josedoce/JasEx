package com.jasex.logistica.api.model;
//Usando o padrão DTO aqui...

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.jasex.logistica.domain.model.StatusEntrega;

import lombok.Data;

@Data
public class EntregaModel {

  private Long id;
  private ClienteResumoModel cliente;
  private DestinatarioModel destinatario;
  private BigDecimal taxa;
  private StatusEntrega status;
  private OffsetDateTime dataPedido;
  private OffsetDateTime dataFinalizacao;

}