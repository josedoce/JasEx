package com.jasex.logistica.api.service;

import javax.transaction.Transactional;

import com.jasex.logistica.domain.model.Entrega;
import com.jasex.logistica.domain.model.StatusEntrega;
import com.jasex.logistica.domain.repository.EntregaRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor 
@Service
public class FinalizacaoEntregaService {
  private BuscaEntregaService buscaEntregaService;
  private EntregaRepository entregaRepository;

  @Transactional
  public void finalizar(Long id) {
    Entrega entrega = buscaEntregaService.buscar(id);
    entrega.finalizar();
    entregaRepository.save(entrega);
  }
}
