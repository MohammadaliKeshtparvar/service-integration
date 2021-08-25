package com.example.integration.filter;

import org.springframework.integration.core.MessageSelector;

import java.util.regex.Pattern;

public abstract class BaseFilter implements MessageSelector {

    protected static final Pattern REGEX_CODE = Pattern.compile("[A-Za-z]{2,3}");
    protected static final Pattern REGEX_UUID = Pattern.compile(
            "[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}");
}
