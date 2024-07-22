package com.alexlasota.nbp_demo.client;

import com.alexlasota.nbp_demo.model.ExchangeRateResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "nbpApi", url = "http://api.nbp.pl/api")
public interface NbpApiClient {

    @GetMapping("/exchangerates/tables/A")
    ExchangeRateResponse[] getCurrentRatesA();

    @GetMapping("/exchangerates/tables/B")
    ExchangeRateResponse[] getCurrentRatesB();

    @GetMapping("/exchangerates/rates/{table}/{currency}")
    ExchangeRateResponse getRateForCurrency(@PathVariable("table") String table, @PathVariable("currency") String currency);

    @GetMapping("/exchangerates/rates/{table}/{currency}/{date}")
    ExchangeRateResponse getRateForCurrencyAndDate(@PathVariable("table") String table,
                                                   @PathVariable("currency") String currency,
                                                   @PathVariable("date") String date);
}