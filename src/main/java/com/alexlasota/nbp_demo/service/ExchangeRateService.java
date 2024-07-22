package com.alexlasota.nbp_demo.service;

import com.alexlasota.nbp_demo.client.NbpApiClient;
import com.alexlasota.nbp_demo.model.ExchangeRate;
import com.alexlasota.nbp_demo.model.ExchangeRateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExchangeRateService {


    private final NbpApiClient nbpApiClient;

    public List<ExchangeRate> getCurrentRates() {
        ExchangeRateResponse[] responsesA = nbpApiClient.getCurrentRatesA();
        ExchangeRateResponse[] responsesB = nbpApiClient.getCurrentRatesB();

        List<ExchangeRate> allRates = new ArrayList<>();

        for (ExchangeRateResponse response : responsesA) {
            allRates.addAll(response.getRates());
        }

        for (ExchangeRateResponse response : responsesB) {
            allRates.addAll(response.getRates());
        }

        return allRates;
    }


    public ExchangeRate getRateForCurrency(String table, String currency) {
        List<ExchangeRate> allRates = getCurrentRates();
        return allRates.stream()
                .filter(rate -> currency.equalsIgnoreCase(rate.getCode()))
                .findFirst()
                .orElse(null);
    }

    public ExchangeRate getRateForCurrencyAndDate(String table, String currency, LocalDate date) {
        List<ExchangeRate> allRates = getCurrentRates();
        return allRates.stream()
                .filter(rate -> currency.equalsIgnoreCase(rate.getCode()))
                .findFirst()
                .orElse(null);
    }
}