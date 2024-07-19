package com.alexlasota.nbp_demo.model;

import lombok.Data;

import java.util.List;

@Data
public class ExchangeRateResponse {
    private String table;
    private String currency;
    private String code;
    private List<ExchangeRate> rates;
}