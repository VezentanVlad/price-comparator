package com.market.price_comparator.controller;
import com.market.price_comparator.dto.DiscountCsvDto;
import com.market.price_comparator.model.Product;
import com.market.price_comparator.repository.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {
        if (productRepository.existsById(product.getProductId())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        Product savedProduct = productRepository.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> getProduct(@PathVariable String id) {
        return productRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productRepository.findAll());
    }

    @PutMapping(value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> updateProduct(@PathVariable String id,
                                                 @Valid @RequestBody Product update) {
        return productRepository.findById(id)
                .map(existing -> {
                    existing.setProductName(update.getProductName());
                    existing.setProductCategory(update.getProductCategory());
                    existing.setBrand(update.getBrand());
                    existing.setPackageQuantity(update.getPackageQuantity());
                    existing.setPackageUnit(update.getPackageUnit());
                    existing.setPrice(update.getPrice());
                    existing.setCurrency(update.getCurrency());
                    return ResponseEntity.ok(productRepository.save(existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }


}

