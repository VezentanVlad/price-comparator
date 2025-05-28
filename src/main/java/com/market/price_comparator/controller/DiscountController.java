package com.market.price_comparator.controller;
import com.market.price_comparator.service.DiscountImportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/discounts")
public class DiscountController {

    private final DiscountImportService discountImportService;

    public DiscountController(DiscountImportService discountImportService) {
        this.discountImportService = discountImportService;
    }

    @PostMapping("/import")
    public ResponseEntity<String> importDiscounts(@RequestParam("file") MultipartFile file) {
        try {
            discountImportService.importDiscounts(file);
            return ResponseEntity.ok("Discounts imported successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error importing discounts: " + e.getMessage());
        }
    }
}
