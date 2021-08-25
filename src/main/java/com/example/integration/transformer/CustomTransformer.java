package com.example.integration.transformer;

import com.example.integration.dto.CapitalResponse;
import com.example.integration.schemas.CapitalCity;
import com.example.integration.schemas.CapitalCityResponse;
import org.springframework.messaging.Message;

public class CustomTransformer {
    public CapitalCity transform(Message<?> message) {
        var capitalCity = new CapitalCity();
        capitalCity.setSCountryISOCode(message.getPayload().toString().split(",")[0].toUpperCase());
        System.out.println(capitalCity.getSCountryISOCode());
        return capitalCity;
    }

    public CapitalResponse responseTransform(Message<CapitalCityResponse> message) {
        return new CapitalResponse(0, message.getPayload().getCapitalCityResult());
    }
}