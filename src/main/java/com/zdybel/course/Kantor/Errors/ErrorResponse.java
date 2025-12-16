package com.zdybel.course.Kantor.Errors;

public class ErrorResponse {
    private final String code; // Techniczny kod błędu
    private final String message; // Przyjazny komunikat dla użytkownika

    public ErrorResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
