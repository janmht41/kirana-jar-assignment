package com.assignment.KiranaService.utility;


import com.assignment.KiranaService.model.ExchangeResponseModel;
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
    public ExchangeResponseModel getExchangeRateInfo(){
         return webClientBuilder.build()
                .get()
                .uri(apiUrl)
                .retrieve()
                .bodyToMono(ExchangeResponseModel.class)
                .block();
    }
}
