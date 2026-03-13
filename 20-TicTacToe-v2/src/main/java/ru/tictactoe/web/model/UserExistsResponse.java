package ru.tictactoe.web.model;

public class UserExistsResponse {
    private String message;

    public UserExistsResponse() {};

    public UserExistsResponse(String message) {
        this.message = message;
    }

    public String getMessage() { return message; }
}
