package com.example.integration.filter;

import org.springframework.messaging.Message;

public class CodeFilter extends BaseFilter {

    @Override
    public boolean accept(Message<?> message) {
        return REGEX_CODE.matcher(message.getPayload().toString().split(",")[0]).matches();
    }

}
