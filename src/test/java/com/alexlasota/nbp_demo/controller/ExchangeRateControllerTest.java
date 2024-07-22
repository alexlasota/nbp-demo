package com.alexlasota.nbp_demo.controller;

import com.alexlasota.nbp_demo.model.ExchangeRate;
import com.alexlasota.nbp_demo.service.ExchangeRateService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExchangeRateControllerTest {

    @Mock
    private ExchangeRateService exchangeRateService;

    @InjectMocks
    private ExchangeRateController exchangeRateController;

    @Test
    void getCurrentRates() {
        ExchangeRate rate1 = new ExchangeRate();
        rate1.setCurrency("Euro");
        rate1.setCode("EUR");
        rate1.setMid(new BigDecimal("4.5"));

        ExchangeRate rate2 = new ExchangeRate();
        rate2.setCurrency("US Dollar");
        rate2.setCode("USD");
        rate2.setMid(new BigDecimal("3.8"));

        when(exchangeRateService.getCurrentRates()).thenReturn(List.of(rate1, rate2));

        ResponseEntity<List<ExchangeRate>> response = exchangeRateController.getCurrentRates();

        assertEquals(2, response.getBody().size());
        assertEquals("Euro", response.getBody().get(0).getCurrency());
        assertEquals("US Dollar", response.getBody().get(1).getCurrency());
        assertEquals(200, response.getStatusCodeValue());
        verify(exchangeRateService, times(1)).getCurrentRates();
    }

    @Test
    void getRateForCurrency() {
        ExchangeRate rate = new ExchangeRate();
        rate.setCurrency("Euro");
        rate.setCode("EUR");
        rate.setMid(new BigDecimal("4.5"));

        when(exchangeRateService.getRateForCurrency("A", "EUR")).thenReturn(rate);

        ResponseEntity<ExchangeRate> response = exchangeRateController.getRateForCurrency("A", "EUR");

        assertEquals("Euro", response.getBody().getCurrency());
        assertEquals(200, response.getStatusCodeValue());
        verify(exchangeRateService, times(1)).getRateForCurrency("A", "EUR");
    }

    @Test
    void getRateForCurrencyAndDate() {
        ExchangeRate rate = new ExchangeRate();
        rate.setCurrency("Euro");
        rate.setCode("EUR");
        rate.setMid(new BigDecimal("4.5"));

        LocalDate date = LocalDate.of(2023, 1, 1);
        when(exchangeRateService.getRateForCurrencyAndDate("A", "EUR", date)).thenReturn(rate);

        ResponseEntity<ExchangeRate> response = exchangeRateController.getRateForCurrencyAndDate("A", "EUR", date);

        assertEquals("Euro", response.getBody().getCurrency());
        assertEquals(200, response.getStatusCodeValue());
        verify(exchangeRateService, times(1)).getRateForCurrencyAndDate("A", "EUR", date);
    }
}