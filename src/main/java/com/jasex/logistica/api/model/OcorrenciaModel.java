package com.jasex.logistica.api.model;

import java.time.OffsetDateTime;

import lombok.Data;

@Data
public class OcorrenciaModel {
  private Long id;
  private String descricao;
  private OffsetDateTime dataRegistro; 
}
