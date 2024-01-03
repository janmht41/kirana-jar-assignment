package com.assignment.KiranaService.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
@Getter
public class ExchangeResponseModel {
    private String baseCurrency;
    private Map<String, Double> rates;
}
