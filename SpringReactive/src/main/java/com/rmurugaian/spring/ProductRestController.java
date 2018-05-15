package com.rmurugaian.spring;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * @author rmurugaian 2018-04-25
 */
@RestController
public class ProductRestController {

    private final ProductService productService;

    public ProductRestController(final ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public Flux<Product> products() {

        return productService.findAll();
    }
}
