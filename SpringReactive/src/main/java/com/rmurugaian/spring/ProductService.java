package com.rmurugaian.spring;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

/**
 * @author rmurugaian 2018-04-25
 */
@Service
public class ProductService {

    private final MongoOperations productRepository;

    public ProductService(final MongoOperations productRepository) {
        this.productRepository = productRepository;
    }

    public Flux<Product> findAll() {
        return Flux.fromIterable(productRepository.findAll(Product.class));
    }
}
