package ru.tictactoe.web.model;

public class InvalidUserNameOrPasswordResponse {
    private String message;

    public InvalidUserNameOrPasswordResponse() {};

    public InvalidUserNameOrPasswordResponse(String message) {
        this.message = message;
    };

    public String getMessage() { return message; }
}
