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
  /**
   * @Transactional declara que o metodo deve
   * ser executado dentro de uma transação...
   * Isso assegura que se algo der errado neste metodo
   * alterações serão descartadas no banco de dados.
   * 
   * Termo leigo para descrever isso: Ou tudo ou nada...
   * 
   * OBS->Só é necessário ser for para salvar ou 
   * excluir dados no banco de dados.
  */
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
