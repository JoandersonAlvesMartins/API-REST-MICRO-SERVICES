package com.joanderson.API_REST_MICRO_SERVICES.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.hateoas.RepresentationModel;

// Mapeamento jpa para a classe ProductModel
// 'jakarta.persistence' é um pacote que fornece a API de persistência do java EE
@Entity
@Table(name = "TB_PRODUCTS")

// hateoas - hypermedia as the engine of application state
// RepresentationModel é uma classe base para representar um recurso do Spring
// HATEOAS que representa um recurso com links
public class ProductModel extends RepresentationModel<ProductModel> implements Serializable {

  /**
   * ' implements Serializable ' informa que essa classe pode ser
   * serializada
   * e desserializada, ou seja, convertida em um fluxo de bytes e depois
   * convertido para um objeto
   */

  private static final long serialVersionUID = 1L;
  // 'serialVersionUID' é um identificador único para cada versão
  // de uma classe que implementa a interface Serializable

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO) // Auto-increment ID da tabela

  private UUID idProduct; // ID do produto
  private String nameProduct; // Nome do produto
  private BigDecimal priceProduct; // Valor do produto
  private String descriptionProduct; // Descrição do produto

  // Metodos Get e Set

  public UUID getIdProduct() {
    return idProduct;
  }

  public void setIdProduct(UUID idProduct) {
    this.idProduct = idProduct;
  }

  public String getNameProduct() {
    return nameProduct;
  }

  public void setNameProduct(String nameProduct) {
    this.nameProduct = nameProduct;
  }

  public BigDecimal getPriceProduct() {
    return priceProduct;
  }

  public void setPriceProduct(BigDecimal priceProduct) {
    this.priceProduct = priceProduct;
  }

  public String getDescriptionProduct() {
    return descriptionProduct;
  }

  public void setDescriptionProduct(String descriptionProduct) {
    this.descriptionProduct = descriptionProduct;
  }

}
