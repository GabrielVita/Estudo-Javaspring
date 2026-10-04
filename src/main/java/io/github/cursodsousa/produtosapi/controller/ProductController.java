package io.github.cursodsousa.produtosapi.controller;

import io.github.cursodsousa.produtosapi.model.Product;
import io.github.cursodsousa.produtosapi.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("products")
public class ProductController {

    private ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostMapping
    public Product save(@RequestBody Product product){
        System.out.println("Produto recebido : " + product);
        var id = UUID.randomUUID().toString();
        product.setId(id);
        productRepository.save(product);
        return product;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getId(@PathVariable("id") String id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Produto com ID " + id + " não foi encontrado."
                ));

        return ResponseEntity.ok(product);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable("id") String id){
        productRepository.deleteById(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Item excluído com sucesso");
        System.out.println("Item excluído com sucesso");

        return ResponseEntity.ok(response);

    }

    @PutMapping("{id}")
    public ResponseEntity<Map<String, String>> updateId(@PathVariable("id") String id,
                                                        @RequestBody Product product){

        product.setId(id);
        productRepository.save(product);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Item " + product.getName() + " alterado com sucesso");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/")
    public List<Product> find(@RequestParam("name") String name ){
        return productRepository.findByName(name);
    }
}
