package com.market.price_comparator.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

    @RestController
    public class HomeController {

        @GetMapping("/")
        public String home() {
            return "Aplicația Comparator de Prețuri este fională!";
        }
    }

