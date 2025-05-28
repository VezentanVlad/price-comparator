package com.market.price_comparator.dto;

import com.market.price_comparator.model.Product;
import com.opencsv.bean.CsvBindByName;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class DiscountCsvDto {
    @CsvBindByName(column = "product_id")
    private String product_id;

    @CsvBindByName(column = "product_name")
    private String product_name;

    @CsvBindByName(column = "brand")
    private String brand;

    @CsvBindByName(column = "package_quantity")
    private Double package_quantity;

    @CsvBindByName(column = "package_unit")
    private String package_unit;

    @CsvBindByName(column = "product_category")
    private String product_category;

    @CsvBindByName(column = "from_date")
    private String from_date;

    @CsvBindByName(column = "to_date")
    private String to_date;

    @CsvBindByName(column = "percentage_of_discount")
    private Double percentage_of_discount;
}

