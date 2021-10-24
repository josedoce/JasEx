package com.jasex.logistica.api.service;

import com.jasex.logistica.api.exception.EntidadeNaoEncontradaException;
import com.jasex.logistica.domain.model.Entrega;
import com.jasex.logistica.domain.repository.EntregaRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BuscaEntregaService {
  private EntregaRepository entregaRepository;

  public Entrega buscar(Long id) {
    return entregaRepository.findById(id)
    .orElseThrow(()-> new EntidadeNaoEncontradaException("Entrega não encontrada."));
  }
}
