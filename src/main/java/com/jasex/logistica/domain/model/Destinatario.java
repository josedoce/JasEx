package com.jasex.logistica.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
@Embeddable //diz que poderá ser embutido em uma tabela.
public class Destinatario {

  @NotBlank
  @Column(name="destinatario_nome")
  private String nome;

  @NotBlank
  @Column(name="destinatario_logradouro")
  private String logradouro;
  
  @NotBlank
  @Column(name="destinatario_numero")
  private String numero;
  
  @NotBlank
  @Column(name="destinatario_complemento")
  private String complemento;
  
  @NotBlank
  @Column(name="destinatario_bairro")
  private String bairro;

}
