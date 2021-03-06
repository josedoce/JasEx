package com.jasex.logistica.api.controller;

import java.util.List;

import javax.validation.Valid;

import com.jasex.logistica.api.assembler.EntregaAssembler;
import com.jasex.logistica.api.model.EntregaModel;
import com.jasex.logistica.api.model.input.EntregaInput;
import com.jasex.logistica.api.service.FinalizacaoEntregaService;
import com.jasex.logistica.api.service.SolicitacaoEntregaService;
import com.jasex.logistica.domain.model.Entrega;
import com.jasex.logistica.domain.repository.EntregaRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RequestMapping("/entregas")
public class EntregaController {
  private SolicitacaoEntregaService solicitacaoEntregaService;
  private EntregaRepository entregaRepository;
  private FinalizacaoEntregaService finalizacaoEntregaService;
  private EntregaAssembler entregaAssembler;

  @PostMapping 
  @ResponseStatus(HttpStatus.CREATED)
  public EntregaModel solicitar(@Valid @RequestBody EntregaInput entregaInput) {
    Entrega entregaModel = entregaAssembler.toEntity(entregaInput);
    Entrega entregaSolicitada = solicitacaoEntregaService.solicitar(entregaModel);
    return entregaAssembler.toModel(entregaSolicitada);
  }
  
  @PutMapping("/{id}/finalizacao")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void finalizar(@PathVariable Long id) {
    finalizacaoEntregaService.finalizar(id);
  }

  @GetMapping
  public List<EntregaModel> listar() {
    List<Entrega> entregas = entregaRepository.findAll();
    return entregaAssembler.toCollectionModel(entregas);
  }

  @GetMapping("/{id}")
  public ResponseEntity<EntregaModel> buscar(@PathVariable Long id) {
    return entregaRepository.findById(id)
      .map((dados)->{
        EntregaModel entregaModel = entregaAssembler.toModel(dados);
        return ResponseEntity.status(HttpStatus.OK).body(entregaModel);
      })
      .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
  }
}
