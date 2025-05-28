package com.market.price_comparator.service;

import com.market.price_comparator.dto.DiscountCsvDto;
import com.market.price_comparator.model.Discount;
import com.market.price_comparator.repository.DiscountRepository;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class DiscountImportService {

    private final DiscountRepository discountRepository;
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public DiscountImportService(DiscountRepository discountRepository) {
        this.discountRepository = discountRepository;
    }

    public void importDiscounts(MultipartFile file) throws Exception {
        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            CsvToBean<DiscountCsvDto> csvToBean = new CsvToBeanBuilder<DiscountCsvDto>(reader)
                    .withType(DiscountCsvDto.class)
                    .withSeparator(';')
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            List<DiscountCsvDto> discounts = csvToBean.parse();

            discounts.forEach(dto -> {
                Discount discount = new Discount();
                discount.setProductId(dto.getProduct_id());
                discount.setProductName(dto.getProduct_name());
                discount.setBrand(dto.getBrand());
                discount.setPackageQuantity(dto.getPackage_quantity());
                discount.setPackageUnit(dto.getPackage_unit());
                discount.setProductCategory(dto.getProduct_category());
                discount.setFromDate(LocalDate.parse(dto.getFrom_date(), dateFormatter));
                discount.setToDate(LocalDate.parse(dto.getTo_date(), dateFormatter));
                discount.setPercentageOfDiscount(dto.getPercentage_of_discount());

                discountRepository.save(discount);
            });
        }
    }
}