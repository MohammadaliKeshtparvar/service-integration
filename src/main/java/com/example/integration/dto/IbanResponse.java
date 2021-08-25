package com.example.integration.dto;

public class IbanResponse extends BaseResponse{

    private String iban;

    public IbanResponse(int responseCode) {
        super(responseCode);
    }

    public IbanResponse(int responseCode, String iban) {
        super(responseCode);
        this.iban = iban;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }
}
