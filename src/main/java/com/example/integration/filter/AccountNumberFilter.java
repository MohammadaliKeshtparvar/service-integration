package com.example.integration.filter;

import com.example.integration.exception.InvalidAccountNumberException;
import org.springframework.messaging.Message;

import java.util.regex.Pattern;

public class AccountNumberFilter extends BaseFilter{

    private static final Pattern REGEX_ACCOUNT_NUMBER = Pattern.compile("[0-9]{13}");

    @Override
    public boolean accept(Message<?> message) {
        System.out.println(message.getPayload());
        if (!REGEX_ACCOUNT_NUMBER.matcher(message
                .getPayload()
                .toString()
                .replace("[", "")
                .replace("]", ""))
                .matches())
            throw new InvalidAccountNumberException();
        return true;
    }
}
