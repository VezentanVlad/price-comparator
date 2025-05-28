package com.market.price_comparator.service;

import com.market.price_comparator.dto.ProductPriceComparison;
import com.market.price_comparator.model.Product;
import com.market.price_comparator.repository.ProductRepository;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PriceComparisonService {
    private final ProductRepository productRepository;

    public PriceComparisonService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductPriceComparison> comparePrices(String name, String category) {
        List<Product> products = category == null
                ? productRepository.findByProductNameContainingIgnoreCase(name)
                : productRepository.findByProductNameContainingIgnoreCaseAndProductCategory(name, category);

        return products.stream()
                .map(p -> new ProductPriceComparison(
                        p.getProductName(),
                        p.getStore(),
                        p.getPrice(),
                        p.getPrice() / p.getPackageQuantity(),
                        p.getCurrency()))
                .sorted(Comparator.comparing(ProductPriceComparison::getUnitPrice))
                .collect(Collectors.toList());
    }
}