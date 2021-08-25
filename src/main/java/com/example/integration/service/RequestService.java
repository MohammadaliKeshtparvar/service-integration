package com.example.integration.service;

import com.example.integration.dto.IbanResponse;
import com.example.integration.dto.IsoResponse;
import com.example.integration.dto.TransactionResponse;
import com.example.integration.exception.DatabaseException;
import com.example.integration.exception.InvalidUuidException;
import com.example.integration.infrastructure.DbContext;
import com.example.integration.model.Transaction;
import com.example.integration.schemas.CapitalCity;
import com.example.integration.schemas.CapitalCityResponse;
import com.example.integration.soap.SoapClient;
import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.packager.GenericPackager;
import org.springframework.messaging.Message;


import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;


public class RequestService {

    private static final String url = "http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso?wsdl";
    private final SoapClient soapClient;
    private final DbContext dbContext;
    private static final Pattern REGEX_UUID = Pattern.compile("[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}");

    public RequestService(SoapClient soapClient, DbContext dbContext) {
        this.soapClient = soapClient;
        this.dbContext = dbContext;
    }

    public CapitalCityResponse getResponse(Message<CapitalCity> message) {
        var capitalCityResponse = (CapitalCityResponse) soapClient.callWebService(url, message.getPayload());
        checkResponse(capitalCityResponse);
        dbContext.addTransaction(new Transaction(true, "The request was successful."));
        return capitalCityResponse;
    }

    public IbanResponse getIban(Message<?> message) {
        Iban iban = new Iban
                .Builder()
                .countryCode(CountryCode.IR)
                .bankCode("062")
                .accountNumber("000000"+message.getPayload().toString().replace("[", "").replace("]", ""))
                .build();
        return new IbanResponse(0, iban.toString());
    }

    private void checkResponse(CapitalCityResponse capitalCityResponse) {
        if (capitalCityResponse
                .getCapitalCityResult()
                .equals("Country not found in the database")) {
            dbContext.addTransaction(new Transaction(false, "Country not found in the database."));
            throw new DatabaseException();
        }
    }

    public void failTransaction(Message<?> message) {
        dbContext.addTransaction(new Transaction(false, "ISO code is invalid."));
    }

    private boolean regexUuid(String message) {
        return REGEX_UUID.matcher(message).matches();
    }

    public Message<?> checkUuid(Message<?> message) {
        if (message.getPayload().toString().split(",").length == 2)
            if (!regexUuid(message.getPayload().toString().split(",")[1])) {
                dbContext.addTransaction(new Transaction(false, "The format of UUID is incorrect."));
                throw new InvalidUuidException();
            }
        return message;
    }

    public TransactionResponse getTransactions(Message<?> message) {
        return new TransactionResponse(0, dbContext.getTransactionList());
    }

    public IsoResponse unpackIsoMessage(Message<?> message) throws ISOException {
        InputStream is = getClass().getResourceAsStream("/iso/iso.xml");
        GenericPackager packager = new GenericPackager(is);
        ISOMsg isoMsg = new ISOMsg();
        isoMsg.setPackager(packager);
        System.out.println(message.getPayload());
        isoMsg.unpack(message.getPayload().toString().getBytes());
        return new IsoResponse(0, generateIsoMap(isoMsg));
    }

    private Map<String, String> generateIsoMap(ISOMsg isoMsg) {
        var map = new HashMap<String, String>();
        for (int i = 1; i <= isoMsg.getMaxField(); i++) {
            if (isoMsg.hasField(i))
                map.put(Integer.toString(i), isoMsg.getString(i));
        }
        return map;
    }
}
