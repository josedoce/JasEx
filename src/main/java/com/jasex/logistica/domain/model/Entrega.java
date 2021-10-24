package com.jasex.logistica.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.Valid;
import javax.validation.groups.Default;
import javax.validation.groups.ConvertGroup;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jasex.logistica.api.exception.NegocioException;
import com.jasex.logistica.domain.ValidationGroups;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
//as validações deixaram de ser necessárias, por causa do DTO
@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
public class Entrega {
  @EqualsAndHashCode.Include
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;

  /*@ConvertGroup(from=Default.class, to=ValidationGroups.ClienteId.class)
    Isso ta avisando ao @Valid para validar apenas os
    atributos com uma anotação do grupo ValidationGroups.ClientId.class
  */
  //@Valid //valide o que está dentro de cliente tambem.
  //@ConvertGroup(from=Default.class, to=ValidationGroups.ClienteId.class)
  //@NotNull
  @ManyToOne
  private Cliente cliente;

  //@Valid
  //@NotNull
  @Embedded //embutirá como parte da tabela.
  private Destinatario destinatario;

  //@NotNull
  private BigDecimal taxa;

  //mapeamento bidirecional
  @OneToMany(mappedBy = "entrega", cascade = CascadeType.ALL)
  private List<Ocorrencia> ocorrencias = new ArrayList<>();

  //Atenção: Não é necessário validar atributos READ_ONLY
  //@JsonProperty(access = JsonProperty.Access.READ_ONLY)
  @Enumerated(EnumType.STRING)
  private StatusEntrega status;

  //@JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private OffsetDateTime dataPedido;
  
  //evitando que seja modificado pelo front
  //@JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private OffsetDateTime dataFinalizacao;

  public Ocorrencia adicionarOcorrencia(String descricao) {
    Ocorrencia ocorrencia = new Ocorrencia();
    ocorrencia.setDescricao(descricao);
    ocorrencia.setDataRegistro(OffsetDateTime.now());
    ocorrencia.setEntrega(this);
    this.getOcorrencias().add(ocorrencia);

    return ocorrencia;
  }

  public void finalizar() {
    if(naoPodeSerFinalizada()){
      throw new NegocioException("Entrega não pode ser finalizada");
    }
    this.setStatus(StatusEntrega.FINALIZADA);
    this.setDataFinalizacao(OffsetDateTime.now());
  }
  //questão de legibilidade :P
  private boolean podeSerFinalizada() {
    return StatusEntrega.PENDENTE.equals(getStatus());
  }

  private boolean naoPodeSerFinalizada() {
    return !this.podeSerFinalizada();
  }
}
