package com.example.integration.exception;

import com.example.integration.dto.BaseResponse;
import com.example.integration.dto.ErrorResponse;
import org.jpos.iso.ISOException;
import org.springframework.integration.MessageDispatchingException;


public class ExceptionHandling {
    public BaseResponse test(Exception ex) {
        if (ex.getCause() instanceof DatabaseException)
            return new ErrorResponse(1);
        if (ex.getCause() instanceof MessageDispatchingException)
            return new ErrorResponse(9);
        if (ex.getCause() instanceof InvalidUuidException)
            return new ErrorResponse(10);
        if (ex.getCause() instanceof InvalidAccountNumberException)
            return new ErrorResponse(11);
        if (ex.getCause() instanceof ISOException) {
            System.out.println(ex.getMessage());
            return new ErrorResponse(12);
        }
        return new ErrorResponse(404);
    }
}
