package com.jasex.logistica.api.controller;

import java.util.List;

import javax.validation.Valid;

import com.jasex.logistica.api.service.CatalogoClienteService;
import com.jasex.logistica.domain.model.Cliente;
import com.jasex.logistica.domain.repository.ClienteRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

//Ao usar o @AllArgsConstructor, ele fará os injects
@AllArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteController {

  private ClienteRepository clienteRepository;
  private CatalogoClienteService catalogoClienteService;

  @GetMapping 
  public List<Cliente> obterLista() {
    return clienteRepository.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Cliente> obterCliente(@PathVariable Long id) {
    return clienteRepository.findById(id)
    .map(cliente -> ResponseEntity.status(200).body(cliente))
    .orElse(ResponseEntity.status(404).build());
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public @ResponseBody Cliente criarCliente(@Valid @RequestBody Cliente cliente) {
    return catalogoClienteService.salvar(cliente);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Cliente> atualizarCliente(@PathVariable Long id, @Valid @RequestBody Cliente cliente) {
    return clienteRepository.findById(id)
      .map(exists-> {
        cliente.setId(id);
        catalogoClienteService.salvar(cliente);
        return ResponseEntity.status(200).body(exists);
      })
      .orElse(ResponseEntity.status(404).build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> removerCliente(@PathVariable Long id) {
    if(!clienteRepository.existsById(id))
      return ResponseEntity.status(404).build();

    catalogoClienteService.excluir(id);
    return ResponseEntity.noContent().build();
  }
}
