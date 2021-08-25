package com.example.integration;

import com.example.integration.infrastructure.DbContext;
import com.example.integration.service.RequestService;
import com.example.integration.soap.SoapConfig;
import com.jayway.restassured.internal.RequestSpecificationImpl;
import com.jayway.restassured.specification.RequestSpecification;
import com.jayway.restassured.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.http.MockHttpInputMessage;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import static com.jayway.restassured.RestAssured.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


//@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "/config/integration.xml", classes = {SoapConfig.class, DbContext.class})
@SpringBootTest
@AutoConfigureMockMvc
class IntegrationApplicationTests {

//    @Autowired
//    private WebApplicationContext webApplicationContext;
//    @Mock
//    private SoapClient soapClient;



    @Autowired
    private MockMvc mockMvc;
//    private MockHttpInputMessage mockHttpInputMessage;
    /*@BeforeEach
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }*/


    @RepeatedTest(2)
    void contextLoads() throws Exception {

        RequestSpecification requestSpecification = given();

//        requestSpecification.contentType("").params().then().post().body().toString();



//        IntegrationApplication integrationApplication = new IntegrationApplication();
//        CapitalCityResponse capitalCityResponse = new CapitalCityResponse();
//        Mockito.when(soapClient.callWebService(
//                "https://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso?wsdl", "ir"))
//        .thenReturn(capitalCityResponse);
//        assertEquals("Tehran", capitalCityResponse.getCapitalCityResult());

//        mockMvc.perform()


        ///////////////////////

//        assert this.mockMvc.getDispatcherServlet().getHandlerMappings() != null;
//        this.mockMvc.getDispatcherServlet().getHandlerMappings().forEach(System.out::println);
//        this.mockMvc.perform(get("/country/i2")).andDo(print())
//                .andExpect(status().is2xxSuccessful());
//        assert this.mockMvc.getDispatcherServlet().getHandlerMappings() != null;
//        this.mockMvc.getDispatcherServlet().getHandlerMappings().forEach(System.out::println);
//        this.mockMvc.perform(get("/country/ir")).andDo(print())
//                .andExpect(status().is2xxSuccessful());
//        this.mockMvc.perform(get("/country/te")).andDo(print())
//                .andExpect(status().is2xxSuccessful());
//
        given().queryParam("iban","983469836")
                .when().get("http://localhost:8080").then().log()
                .body()
                .contentType("json");
    }
}
