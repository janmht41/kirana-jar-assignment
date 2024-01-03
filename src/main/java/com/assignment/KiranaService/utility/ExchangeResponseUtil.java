package com.assignment.KiranaService.utility;


import com.assignment.KiranaService.model.ExchangeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;


@Component
public class ExchangeResponseUtil {
    @Autowired
    private WebClient.Builder webClientBuilder;
    @Value("${rate-conversion.url}")
    private String apiUrl;
    public  ExchangeResponse getExchangeRateInfo(){
         return webClientBuilder.build()
                .get()
                .uri(apiUrl)
                .retrieve()
                .bodyToMono(ExchangeResponse.class)
                .block();
    }
}
