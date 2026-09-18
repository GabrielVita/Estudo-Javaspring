package io.github.cursodsousa.produtosapi.controller;

import io.github.cursodsousa.produtosapi.model.Product;
import io.github.cursodsousa.produtosapi.repository.ProductRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
