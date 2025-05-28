package com.market.price_comparator.repository;


import com.market.price_comparator.model.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface DiscountRepository extends JpaRepository<Discount, Long> {
    List<Discount> findByFromDateLessThanEqualAndToDateGreaterThanEqual(LocalDate date, LocalDate sameDate);
    List<Discount> findByProductId(String productId);
}


/*public interface DiscountRepository extends JpaRepository<Discount, Long> {
    List<Discount> findByFromDateLessThanEqualAndToDateGreaterThanEqual(LocalDate start, LocalDate end);
}

 */