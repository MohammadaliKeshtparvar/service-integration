package com.example.integration.service;

import com.example.integration.infrastructure.DbContext;
import com.example.integration.schemas.CapitalCity;
import com.example.integration.schemas.CapitalCityResponse;
import com.example.integration.soap.SoapClient;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class RequestServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SoapClient soapClient;

    @MockBean
    private DbContext dbContext;

    @Test
    void getTransactions() throws Exception {
        CapitalCity c = new CapitalCity();
        c.setSCountryISOCode("IR");
        CapitalCityResponse cr = new CapitalCityResponse();
        cr.setCapitalCityResult("Tehran");
        when(soapClient
                .callWebService("http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso?wsdl"
                        , c)).thenReturn(cr);
        mockMvc.perform(get("/transactions/all"))
                .andDo(print())
                .andExpect(status().isOk());
    }

}