package ru.tictactoe.web.model;

import java.util.UUID;

public class CreateUserResponse {
    private String message;
    private UUID userId;

    public CreateUserResponse() {};

    public CreateUserResponse(String message, UUID userId) {
        this.message = message;
        this.userId = userId;
    }

    public String getMessage() { return message; }
    public UUID getUserId() { return userId; }
}
