package com.jasex.logistica.domain.repository;

import java.util.List;
import java.util.Optional;

import com.jasex.logistica.domain.model.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

  List<Cliente> findByNome(String nome);

  List<Cliente> findByNomeContaining(String nome);

  Optional<Cliente> findByEmail(String email);
}
