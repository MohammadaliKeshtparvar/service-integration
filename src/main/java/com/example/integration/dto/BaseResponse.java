package com.example.integration.dto;

import java.util.HashMap;
import java.util.Map;

public abstract class BaseResponse {

    private int responseCode;
    private final String message;
    private final static Map<Integer, String> responseCodeMessage;
    static {
        responseCodeMessage = new HashMap<>();
        responseCodeMessage.put(0, "Successful");
        responseCodeMessage.put(1, "Not found in database.");
        responseCodeMessage.put(2, "Internal service error");
        responseCodeMessage.put(3, "Service unavailable");
        responseCodeMessage.put(4, "Request method not supported.");
        responseCodeMessage.put(5, "Media type not supported.");
        responseCodeMessage.put(6, "Media type not acceptable.");
        responseCodeMessage.put(7, "URL is wrong.");
        responseCodeMessage.put(8, "ISO code is null.");
        responseCodeMessage.put(9, "Invalid ISO code parameter");
        responseCodeMessage.put(10, "Invalid UUID parameter");
        responseCodeMessage.put(11, "Invalid account number parameter");
        responseCodeMessage.put(12, "ISO exception occurred.");
        responseCodeMessage.put(404, "Bad request.");
    }

    public BaseResponse(int responseCode) {
        this.responseCode = responseCode;
        this.message = responseCodeMessage.get(responseCode);
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getMessage() {
        return message;
    }
}
