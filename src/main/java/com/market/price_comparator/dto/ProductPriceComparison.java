package com.market.price_comparator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

    @Data
    @AllArgsConstructor
    public class ProductPriceComparison {
        private String productName;
        private String store;
        private Double price;
        private Double unitPrice;
        private String currency;
    }


