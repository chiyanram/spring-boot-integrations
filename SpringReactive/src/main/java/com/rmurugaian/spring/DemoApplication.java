package com.rmurugaian.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.core.MongoOperations;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@SpringBootApplication
public class DemoApplication {

    private static final AtomicLong ATOMIC_LONG = new AtomicLong(1);
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    @Value("${product.input}")
    private Resource resource;


    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(final MongoOperations mongoOperations) {

        return (String... args) -> {
            mongoOperations.dropCollection(Product.class);
            mongoOperations.createCollection(Product.class);
            final List<Product> products = getProducts();
            products.forEach(mongoOperations::save);
            mongoOperations.findAll(Product.class).stream().forEach(System.out::println);
        };


    }

    public List<Product> getProducts() {
        try {
            return Files
                .readAllLines(resource.getFile().toPath())
                .stream()
                .map(line -> line.split(","))
                .map(array -> new Product(ATOMIC_LONG.getAndIncrement(), array[0], array[1]))
                .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
