package io.github.cursodsousa.produtosapi.controller;

import io.github.cursodsousa.produtosapi.model.Product;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("products")
public class ProductController {

    @PostMapping
    public Product save(@RequestBody Product product){
        System.out.println("Produto recebido : " + product);
        return product;
    }
}
