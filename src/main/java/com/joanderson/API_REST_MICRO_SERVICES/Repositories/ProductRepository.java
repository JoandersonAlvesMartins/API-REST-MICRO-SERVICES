package com.joanderson.API_REST_MICRO_SERVICES.Repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.joanderson.API_REST_MICRO_SERVICES.Models.ProductModel;

// interface ProductRepository
// 'ProductRepository' é uma interface que representa um repositório de produtos
// é um padrão de projeto que fornece uma abstração para o acesso a dados
// é utilizado para separar a lógica de acesso a dados da lógica de negócio, manutenção e a evolução do código, 
// implementação de testes automatizados e implementação de padrões de projeto
// esteriotipo 'Repository' é utilizado para indicar que essa interface é um repositório
@Repository // esteriotipo bean '@Repository' indica que é gerenciado pelo proprio spring
public interface ProductRepository extends JpaRepository<ProductModel, UUID> {
  // 'JpaRepository' é uma interface do Spring Data JPA que fornece métodos
  // para realizar operações de CRUD (Create, Read, Update, Delete) em entidades
  // 'UUID' é um tipo de dado que representa um identificador único universal
  // 'ProductModel'(entidade) é a classe que representa o modelo de produto
  // 'ProductRepository' é uma interface que estende a interface JpaRepository
  // e herda todos os métodos da interface JpaRepository e seus atributos

}
