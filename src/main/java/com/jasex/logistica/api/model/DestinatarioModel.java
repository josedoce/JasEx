package com.jasex.logistica.api.model;

import lombok.Data;

@Data
public class DestinatarioModel {
  private String nome;
  private String logradouro;
  private String numero;
  private String complemento;
  private String bairro;
}
