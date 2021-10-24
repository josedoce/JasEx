package com.jasex.logistica.api.controller;

import java.util.List;

import javax.validation.Valid;

import com.jasex.logistica.api.assembler.OcorrenciaAssembler;
import com.jasex.logistica.api.model.OcorrenciaModel;
import com.jasex.logistica.api.model.input.OcorrenciaInput;
import com.jasex.logistica.api.service.BuscaEntregaService;
import com.jasex.logistica.api.service.RegistroOcorrenciaService;
import com.jasex.logistica.domain.model.Entrega;
import com.jasex.logistica.domain.model.Ocorrencia;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas/{id}/ocorrencias")
public class OcorrenciaController {
  private BuscaEntregaService buscaEntregaService;
  private OcorrenciaAssembler ocorrenciaAssembler;
  private RegistroOcorrenciaService registroOcorrenciaService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public OcorrenciaModel registrar(@PathVariable Long id, @Valid @RequestBody OcorrenciaInput ocorrenciaInput) {
    Ocorrencia ocorrencia = registroOcorrenciaService.registrar(id, ocorrenciaInput.getDescricao());
    return ocorrenciaAssembler.toModel(ocorrencia);
  }

  @GetMapping
  public List<OcorrenciaModel> listar(@PathVariable Long id) {
    Entrega entrega = buscaEntregaService.buscar(id);
    return ocorrenciaAssembler.toCollectionModel(entrega.getOcorrencias());
  }
}
