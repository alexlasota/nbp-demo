package com.alexlasota.nbp_demo.controller;

import com.alexlasota.nbp_demo.model.ExchangeRate;
import com.alexlasota.nbp_demo.service.ExchangeRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ExchangeRateController {

    private final ExchangeRateService exchangeRateService;

    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Application is up and running");
    }

    @GetMapping("/rates")
    public ResponseEntity<List<ExchangeRate>> getCurrentRates() {
        return ResponseEntity.ok(exchangeRateService.getCurrentRates());
    }

    @GetMapping("/rates/{currency}")
    public ResponseEntity<ExchangeRate> getRateForCurrency(@PathVariable String currency) {
        return ResponseEntity.ok(exchangeRateService.getRateForCurrency(currency));
    }

    @GetMapping("/rates/{currency}/{date}")
    public ResponseEntity<ExchangeRate> getRateForCurrencyAndDate(
            @PathVariable String currency,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(exchangeRateService.getRateForCurrencyAndDate(currency, date));
    }
}