package com.zdybel.course.Kantor.Errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Obsługa niestandardowego wyjątku InsufficientFundsException
    @ExceptionHandler(InsufficientFundsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // Status HTTP 400
    @ResponseBody
    public ErrorResponse handleInsufficientFunds(InsufficientFundsException ex) {
        return new ErrorResponse(
                "INSUFFICIENT_FUNDS",
                ex.getMessage()
        );
    }

    // Obsługa niestandardowego wyjątku DuplicateCurrencyException
    @ExceptionHandler(DuplicateCurrencyException.class)
    @ResponseStatus(HttpStatus.CONFLICT) // Status HTTP 409
    @ResponseBody
    public ErrorResponse handleDuplicateCurrency(DuplicateCurrencyException ex) {
        return new ErrorResponse(
                "DUPLICATE_CURRENCY", // Kod błędu dla frontendu
                ex.getMessage()       // Komunikat błędu
        );
    }
}
