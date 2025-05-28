package com.market.price_comparator.repository;
import com.market.price_comparator.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {
    List<Product> findByProductNameContainingIgnoreCaseAndProductCategory(String productName, String productCategory);
    List<Product> findByProductNameContainingIgnoreCase(String productName);

}

/* public interface ProductRepository extends JpaRepository<Product, String> {
    List<Product> findByProductNameContainingIgnoreCase(String name);
    List<Product> findByProductNameContainingIgnoreCaseAndProductCategory(String name, String category);
}
*/