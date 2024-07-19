package com.alexlasota.nbp_demo.service;

import com.alexlasota.nbp_demo.client.NbpApiClient;
import com.alexlasota.nbp_demo.model.ExchangeRate;
import com.alexlasota.nbp_demo.model.ExchangeRateResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ExchangeRateServiceTest {


    @Mock
    private NbpApiClient nbpApiClient;

    @InjectMocks
    private ExchangeRateService exchangeRateService;

    @Test
    void getCurrentRates() {
        ExchangeRateResponse response = new ExchangeRateResponse();
        ExchangeRate rate = new ExchangeRate();
        rate.setCurrency("Euro");
        rate.setCode("EUR");
        rate.setMid(new BigDecimal("4.5"));
        response.setRates(List.of(rate));

        when(nbpApiClient.getCurrentRates()).thenReturn(new ExchangeRateResponse[]{response});

        List<ExchangeRate> result = exchangeRateService.getCurrentRates();

        assertEquals(1, result.size());
        assertEquals("Euro", result.get(0).getCurrency());
        verify(nbpApiClient, times(1)).getCurrentRates();
    }

    @Test
    void getRateForCurrency() {
        ExchangeRateResponse response = new ExchangeRateResponse();
        ExchangeRate rate = new ExchangeRate();
        rate.setCurrency("Euro");
        rate.setCode("EUR");
        rate.setMid(new BigDecimal("4.5"));
        response.setRates(List.of(rate));

        when(nbpApiClient.getRateForCurrency("EUR")).thenReturn(response);

        ExchangeRate result = exchangeRateService.getRateForCurrency("EUR");

        assertEquals("Euro", result.getCurrency());
        verify(nbpApiClient, times(1)).getRateForCurrency("EUR");
    }

    @Test
    void getRateForCurrencyAndDate() {
        ExchangeRateResponse response = new ExchangeRateResponse();
        ExchangeRate rate = new ExchangeRate();
        rate.setCurrency("Euro");
        rate.setCode("EUR");
        rate.setMid(new BigDecimal("4.5"));
        response.setRates(List.of(rate));

        LocalDate date = LocalDate.of(2023, 1, 1);
        when(nbpApiClient.getRateForCurrencyAndDate("EUR", "2023-01-01")).thenReturn(response);

        ExchangeRate result = exchangeRateService.getRateForCurrencyAndDate("EUR", date);

        assertEquals("Euro", result.getCurrency());
        verify(nbpApiClient, times(1)).getRateForCurrencyAndDate("EUR", "2023-01-01");
    }
}