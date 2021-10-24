package com.jasex.logistica.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.jasex.logistica.domain.ValidationGroups;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
  /* @NotNull
    O group validation de todas as anotações de validação,
    por padrão é Default.class, então, o @Valid dessa classe
    em outra classe, buscará validar quem faz parte desse grupo,
    ao customizar isso, terá que setar isso na classe que
    usa esta classe para relacionamento...

    @Valid -> Só validará anotações group default

  */

  /* @EqualsAndHashCode.Include
    essa notação só considerá no equals e hashcode esta propriedade. 
    lembra, neh ? o hashcode trabalha em conjunto com o equals :)
  */
  //@NotNull(groups = ValidationGroups.ClienteId.class)
  @EqualsAndHashCode.Include
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  //não aceito nullo e nem vazio("")
  /*
    O valid da outra classe lhe ignorará, pois faz parte
    do group default.
  */
  @NotBlank
  @Size(max=60) 
  private String nome;

  @NotBlank
  @Email
  @Size(max = 255)
  private String email;

  @NotBlank
  @Size(max = 20)
  @Column(name="fone")
  private String telefone;
}
