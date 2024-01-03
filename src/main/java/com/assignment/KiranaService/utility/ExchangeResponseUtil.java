package com.assignment.KiranaService.utility;


import com.assignment.KiranaService.model.ExchangeResponse;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;


@Component
public class ExchangeResponseUtil {
    @Autowired
    private WebClient.Builder webClientBuilder;
    public  ExchangeResponse getExchangeRate(){
        return webClientBuilder.build()
                .get()
                .uri("https://api.fxratesapi.com/latest?base=USD&currencies=INR")
                .retrieve()
                .bodyToMono(ExchangeResponse.class)
                .block();
    }
}
