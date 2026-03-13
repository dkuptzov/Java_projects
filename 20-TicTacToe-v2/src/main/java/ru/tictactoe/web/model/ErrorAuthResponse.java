package ru.tictactoe.web.model;

public class ErrorAuthResponse {
    private String message;

    public ErrorAuthResponse() {};

    public ErrorAuthResponse(String message) {
        this.message = message;
    }

    public String getMessage() { return message; }
}
