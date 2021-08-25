package com.example.integration.soap;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

public class SoapClient extends WebServiceGatewaySupport {
    public Object callWebService(String url, Object request) {
        try {
            return getWebServiceTemplate().marshalSendAndReceive(url, request);
        }catch (Exception e) {
            System.out.println("********************");
            return new RuntimeException();
        }
    }
}
