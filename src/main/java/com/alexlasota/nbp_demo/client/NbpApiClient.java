package com.alexlasota.nbp_demo.client;

import com.alexlasota.nbp_demo.model.ExchangeRateResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "nbpApi", url = "http://api.nbp.pl/api")
public interface NbpApiClient {

    @GetMapping("/exchangerates/tables/A?format=json")
    ExchangeRateResponse[] getCurrentRates();

    @GetMapping("/exchangerates/rates/A/{currency}?format=json")
    ExchangeRateResponse getRateForCurrency(@PathVariable("currency") String currency);

    @GetMapping("/exchangerates/rates/A/{currency}/{date}?format=json")
    ExchangeRateResponse getRateForCurrencyAndDate(@PathVariable("currency") String currency, @PathVariable("date") String date);
}