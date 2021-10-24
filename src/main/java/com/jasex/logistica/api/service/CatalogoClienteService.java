package com.jasex.logistica.api.service;

import com.jasex.logistica.api.exception.NegocioException;
import com.jasex.logistica.domain.model.Cliente;
import com.jasex.logistica.domain.repository.ClienteRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CatalogoClienteService {
  
  private ClienteRepository clienteRepository;

  public Cliente buscar(Long id) {
    return clienteRepository.findById(id)
    .orElseThrow(()->new NegocioException("Cliente não encontrado")); 
  }

  @Transactional
  public Cliente salvar(Cliente cliente){
    boolean emailInUse = clienteRepository
    .findByEmail(cliente.getEmail())
    .stream()
    .anyMatch(clienteExistente -> !clienteExistente.equals(cliente));
    if(emailInUse) {
      throw new NegocioException("Já existe um cliente cadastrado com este e-mail");
    }
    return clienteRepository.save(cliente);
  }

  @Transactional
  public void excluir(Long id) {
    clienteRepository.deleteById(id);
  }

}
