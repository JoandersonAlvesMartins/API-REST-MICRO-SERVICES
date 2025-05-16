package com.joanderson.API_REST_MICRO_SERVICES.DTOs;

import java.math.BigDecimal;
// anotações de validação
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductRecordDTOs(@NotBlank String nameProduct, @NotNull BigDecimal priceProduct,
    @NotBlank String descriptionProduct) {
  // recebe os dados do produto - o idProduto é gerado automaticamente

  // os record só possui os metodos getters
  // record para criação dos DTOs
  // 'record' é uma palavra-chave do Java que define uma classe imutável
  // 'record' é uma classe que é usada para armazenar dados de forma imutável

  // DTO (Data Transfer Object) é um objeto que transporta dados entre processos
  // 'DTO' é um padrão de projeto que tem como objetivo transportar dados entre
}
