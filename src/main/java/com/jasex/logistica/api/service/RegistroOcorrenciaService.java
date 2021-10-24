package com.jasex.logistica.api.service;

import javax.transaction.Transactional;

import com.jasex.logistica.domain.model.Entrega;
import com.jasex.logistica.domain.model.Ocorrencia;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RegistroOcorrenciaService {
  private BuscaEntregaService buscaEntregaService;

  @Transactional
  public Ocorrencia registrar(Long id, String descricao) {
    Entrega entrega = buscaEntregaService.buscar(id);
    return entrega.adicionarOcorrencia(descricao);
  }
}
