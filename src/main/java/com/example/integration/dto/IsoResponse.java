package com.example.integration.dto;

import org.json.JSONObject;

import java.util.Map;

public class IsoResponse extends BaseResponse{

    private Map<String, String> map;

    public IsoResponse(int responseCode) {
        super(responseCode);
    }

    public IsoResponse(int responseCode, Map<String, String> map) {
        super(responseCode);
        this.map = map;
    }

    public Map<String, String> getMap() {
        return map;
    }
}
