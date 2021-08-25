package com.example.integration.dto;

public class CapitalResponse extends BaseResponse{

    private String capital;

    public CapitalResponse(int responseCode) {
        super(responseCode);
    }

    public CapitalResponse(int responseCode, String capital) {
        super(responseCode);
        this.capital = capital;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }
}
