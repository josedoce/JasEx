package com.jasex.logistica.api.service;

import java.time.OffsetDateTime;

import com.jasex.logistica.domain.model.Cliente;
import com.jasex.logistica.domain.model.Entrega;
import com.jasex.logistica.domain.model.StatusEntrega;
import com.jasex.logistica.domain.repository.EntregaRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class SolicitacaoEntregaService {
  private EntregaRepository entregaRepository;
  private CatalogoClienteService catalogoClienteService;

  @Transactional
  public Entrega solicitar(Entrega entrega) {
    Cliente cliente = catalogoClienteService.buscar(entrega.getCliente().getId());
    entrega.setCliente(cliente);
    entrega.setStatus(StatusEntrega.PENDENTE);
    entrega.setDataPedido(OffsetDateTime.now());
    return entregaRepository.save(entrega);
  }
}
