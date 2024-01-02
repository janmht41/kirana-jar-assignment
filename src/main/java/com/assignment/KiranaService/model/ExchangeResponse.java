package com.assignment.KiranaService.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;


import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
public class ExchangeResponse {

    public Map<String, Double> getRates() {
        return rates;
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }

    private String base;
    private Map<String, Double> rates;
}
