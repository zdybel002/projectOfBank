package com.zdybel.course.Kantor.Errors;

// InsufficientFundsException.java


// Zaleca się dziedziczenie po RuntimeException, aby uniknąć konieczności
// jawnego deklarowania 'throws' w metodach.
public class InsufficientFundsException extends RuntimeException {

    // Dodajemy konstruktor, który przyjmuje komunikat o błędzie
    public InsufficientFundsException(String message) {
        super(message);
    }

    // Możesz opcjonalnie dodać konstruktor z przyczyną błędu
    public InsufficientFundsException(String message, Throwable cause) {
        super(message, cause);
    }
}