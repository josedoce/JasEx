package com.jasex.logistica.common;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
  //essa configuração foi necessaria, pois ela não é
  //nativamente gerenciada pelo spring, entao, 
  //anotamos a classe como uma classe de configuração
  //e tornamos gerenciavel pelo spring com o @Bean :) 
  
  @Bean //agora será injetado no modelMapper.
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }
}
