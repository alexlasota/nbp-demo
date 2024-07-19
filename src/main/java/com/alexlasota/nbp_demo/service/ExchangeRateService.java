package com.alexlasota.nbp_demo.service;

import com.alexlasota.nbp_demo.client.NbpApiClient;
import com.alexlasota.nbp_demo.model.ExchangeRate;
import com.alexlasota.nbp_demo.model.ExchangeRateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExchangeRateService {


    private final NbpApiClient nbpApiClient;

    public List<ExchangeRate> getCurrentRates() {
        ExchangeRateResponse[] responses = nbpApiClient.getCurrentRates();
        if (responses.length > 0) {
            return responses[0].getRates();
        }
        return List.of();
    }

    public ExchangeRate getRateForCurrency(String currency) {
        ExchangeRateResponse response = nbpApiClient.getRateForCurrency(currency);
        return response.getRates().get(0);
    }

    public ExchangeRate getRateForCurrencyAndDate(String currency, LocalDate date) {
        ExchangeRateResponse response = nbpApiClient.getRateForCurrencyAndDate(currency, date.toString());
        return response.getRates().get(0);
    }
}