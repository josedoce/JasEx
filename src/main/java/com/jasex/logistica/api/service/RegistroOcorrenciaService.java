package com.jasex.logistica.api.service;

import javax.transaction.Transactional;

import com.jasex.logistica.api.exception.NegocioException;
import com.jasex.logistica.domain.model.Entrega;
import com.jasex.logistica.domain.model.Ocorrencia;
import com.jasex.logistica.domain.repository.EntregaRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RegistroOcorrenciaService {
  private BuscaEntregaService buscaEntregaService;

  @Transactional
  public Ocorrencia registrar(Long id, String descricao) {
    Entrega entrega = buscaEntregaService.buscar(id);
    //não precisa de sabe nesse caso, o proprio jakarta persistence cuidará disso.
    //para funcionar corretamente, use o CASCADE ALL
    return entrega.adicionarOcorrencia(descricao);
  }
}
