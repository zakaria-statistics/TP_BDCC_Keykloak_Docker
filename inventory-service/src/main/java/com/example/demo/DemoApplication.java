package com.example.demo;

import com.example.demo.Repository.ProductRepository;
import com.example.demo.entities.Product;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {

        SpringApplication.run(DemoApplication.class, args);

    }

    @Bean
    CommandLineRunner commandLineRunner(ProductRepository productRepository){

        return args -> {
            productRepository.save(Product.builder()
                    .id("P01")
                    .name("Computer")
                    .price(8009)
                    .quantity(12)
                    .build());
            productRepository.save(Product.builder()
                    .id("P02")
                    .name("printer")
                    .price(1000)
                    .quantity(5)
                    .build());
            productRepository.save(Product.builder()
                    .id("P03")
                    .name("smartphone")
                    .price(2500)
                    .quantity(10)
                    .build());
        };
    }
}
