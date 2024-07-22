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
        ExchangeRate rateA = new ExchangeRate();
        rateA.setCurrency("USD");
        rateA.setCode("USD");
        rateA.setMid(BigDecimal.valueOf(4.1234));

        ExchangeRate rateB = new ExchangeRate();
        rateB.setCurrency("EUR");
        rateB.setCode("EUR");
        rateB.setMid(BigDecimal.valueOf(4.5678));

        ExchangeRateResponse responseA = new ExchangeRateResponse();
        responseA.setRates(List.of(rateA));

        ExchangeRateResponse responseB = new ExchangeRateResponse();
        responseB.setRates(List.of(rateB));

        when(nbpApiClient.getCurrentRatesA()).thenReturn(new ExchangeRateResponse[]{responseA});
        when(nbpApiClient.getCurrentRatesB()).thenReturn(new ExchangeRateResponse[]{responseB});

        List<ExchangeRate> result = exchangeRateService.getCurrentRates();

        assertEquals(2, result.size());
        assertEquals("USD", result.get(0).getCode());
        assertEquals("EUR", result.get(1).getCode());
    }

    @Test
    void getRateForCurrency() {
        ExchangeRate rateA = new ExchangeRate();
        rateA.setCurrency("USD");
        rateA.setCode("USD");
        rateA.setMid(BigDecimal.valueOf(4.1234));

        ExchangeRate rateB = new ExchangeRate();
        rateB.setCurrency("EUR");
        rateB.setCode("EUR");
        rateB.setMid(BigDecimal.valueOf(4.5678));

        ExchangeRateResponse responseA = new ExchangeRateResponse();
        responseA.setRates(List.of(rateA));

        ExchangeRateResponse responseB = new ExchangeRateResponse();
        responseB.setRates(List.of(rateB));

        when(nbpApiClient.getCurrentRatesA()).thenReturn(new ExchangeRateResponse[]{responseA});
        when(nbpApiClient.getCurrentRatesB()).thenReturn(new ExchangeRateResponse[]{responseB});

        ExchangeRate result = exchangeRateService.getRateForCurrency("A", "EUR");

        assertEquals("EUR", result.getCode());
        verify(nbpApiClient, times(1)).getCurrentRatesA();
        verify(nbpApiClient, times(1)).getCurrentRatesB();
    }

    @Test
    void getRateForCurrencyAndDate() {
        ExchangeRate rateA = new ExchangeRate();
        rateA.setCurrency("USD");
        rateA.setCode("USD");
        rateA.setMid(BigDecimal.valueOf(4.1234));

        ExchangeRate rateB = new ExchangeRate();
        rateB.setCurrency("EUR");
        rateB.setCode("EUR");
        rateB.setMid(BigDecimal.valueOf(4.5678));

        ExchangeRateResponse responseA = new ExchangeRateResponse();
        responseA.setRates(List.of(rateA));

        ExchangeRateResponse responseB = new ExchangeRateResponse();
        responseB.setRates(List.of(rateB));

        when(nbpApiClient.getCurrentRatesA()).thenReturn(new ExchangeRateResponse[]{responseA});
        when(nbpApiClient.getCurrentRatesB()).thenReturn(new ExchangeRateResponse[]{responseB});

        LocalDate date = LocalDate.of(2023, 1, 1);
        ExchangeRate result = exchangeRateService.getRateForCurrencyAndDate("A", "EUR", date);

        assertEquals("EUR", result.getCode());
        verify(nbpApiClient, times(1)).getCurrentRatesA();
        verify(nbpApiClient, times(1)).getCurrentRatesB();
    }
}