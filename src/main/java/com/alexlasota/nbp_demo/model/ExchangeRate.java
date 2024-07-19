package com.alexlasota.nbp_demo.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ExchangeRate {

    private String currency;
    private String code;
    private BigDecimal mid;

}