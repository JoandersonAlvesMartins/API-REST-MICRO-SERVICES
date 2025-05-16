package com.joanderson.API_REST_MICRO_SERVICES.Controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.joanderson.API_REST_MICRO_SERVICES.DTOs.ProductRecordDTOs;
import com.joanderson.API_REST_MICRO_SERVICES.Models.ProductModel;
import com.joanderson.API_REST_MICRO_SERVICES.Repositories.ProductRepository;

import jakarta.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

// esteriotipo bean '@Controller' indica que é gerenciado pelo proprio spring
@RestController // esteriotipo bean '@RestController' indica que é um controlador REST
// 'RestController' é uma anotação do Spring que combina as anotações
// @Controller e @ResponseBody
public class ProductController {

  @Autowired
  ProductRepository productRepository; // ponto de injeção do repositório

  // 'ProductController' é a classe que controla as requisições HTTP
  // metodos do controlador Create, Read, Update e Delete

  // record para criação dos DTOs
  // 'record' é uma palavra-chave do Java que define uma classe imutável
  // 'record' é uma classe que é usada para armazenar dados de forma imutável

  // DTO (Data Transfer Object) é um objeto que transporta dados entre processos
  // 'DTO' é um padrão de projeto que tem como objetivo transportar dados entre

  @PostMapping("/products") // 'PostMapping' é uma anotação do Spring que mapeia requisições HTTP POST
  public ResponseEntity<ProductModel> saveProduct(@RequestBody @Valid ProductRecordDTOs productRecordDTOs) {
    // 'savePointer' é o método que salva o produto no banco de dados
    // 'RequestBody' é uma anotação do Spring que indica que o corpo da requisição
    // deve ser mapeado para o objeto 'productRecordDTOs'
    var productModel = new ProductModel();
    BeanUtils.copyProperties(productRecordDTOs, productModel);
    return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(productModel));
    // 'createProduct' é o método que cria um novo produto
    // 'saveProduct' é o método que salva o produto no banco de dados
  }

  // 'productRecordDTOs' é o objeto que contém os dados do produto
  // 'productRepository' é o repositório que acessa os dados do produto
  // 'ProductModel' é a classe que representa o modelo de produto
  // 'UUID' é um tipo de dado que representa um identificador único universal

  @GetMapping("/products") // 'GetMapping' é uma anotação do Spring que mapeia requisições HTTP GET
  public ResponseEntity<List<ProductModel>> getAllProducts() {
    List<ProductModel> productList = productRepository.findAll();
    // 'productList' é a lista de produtos que será retornada
    if (!productList.isEmpty()) {
      for (ProductModel product : productList) {
        UUID idProduct = product.getIdProduct();
        product.add(linkTo(methodOn(ProductController.class).getOneProduct(idProduct)).withSelfRel());
        // linkTo é o método que cria um link para o produto
        // 'methodOn' é o método que cria um link para o método getOneProduct
        // 'add' é o método que adiciona um link ao produto
      }
    }
    // 'getAllProducts' é o método que retorna todos os produtos
    return ResponseEntity.status(HttpStatus.OK).body(productList);
    // 'findAll' é o método que encontra todos os produtos
  }

  @GetMapping("/products/{idProduct}")
  public ResponseEntity<Object> getOneProduct(@PathVariable(value = "idProduct") UUID idProduct) {
    // 'getOneProduct' é o método que retorna um produto pelo ID
    Optional<ProductModel> productO = productRepository.findById(idProduct);
    // 'findById' é o método que encontra um produto pelo ID
    if (productO.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
      // 'isEmpty' é o método que verifica se o produto existe
    } else {
      productO.get().add(linkTo(methodOn(ProductController.class).getAllProducts()).withRel("Products List"));
      return ResponseEntity.status(HttpStatus.OK).body(productO.get());
      // 'get' é o método que retorna o produto
    }
  }

  @PutMapping("/products/{idProduct}")
  public ResponseEntity<Object> updateProduct(@PathVariable(value = "idProduct") UUID idProduct,
      @RequestBody @Valid ProductRecordDTOs productRecordDTOs) {
    // 'updateProduct' é o método que atualiza um produto
    Optional<ProductModel> productO = productRepository.findById(idProduct); // buscando o produto na base de dados
    // 'findById' é o método que encontra um produto pelo ID
    if (productO.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
      // 'isEmpty' é o método que verifica se o produto existe
    }
    var productModel = productO.get(); // pegando o produto que foi encontrado na base de dados
    BeanUtils.copyProperties(productRecordDTOs, productModel); // convertendo do DTO para o Model
    // 'copyProperties' é o método que copia as propriedades do DTO para o Model
    return ResponseEntity.status(HttpStatus.OK).body(productRepository.save(productModel)); // salvando o produto
  }

  @DeleteMapping("/products/{idProduct}") // 'DeleteMapping' é uma anotação do Spring que mapeia requisições HTTP
  public ResponseEntity<Object> deleteProduct(@PathVariable(value = "idProduct") UUID idProduct) {
    // 'deleteProduct' é o método que deleta um produto
    Optional<ProductModel> productO = productRepository.findById(idProduct); // buscando o produto na base de dados
    // 'findById' é o método que encontra um produto pelo ID
    if (productO.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
      // 'isEmpty' é o método que verifica se o produto existe
    }
    productRepository.delete(productO.get()); // deletando o produto
    return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully");
    // 'delete' é o método que deleta um produto
  }

}
