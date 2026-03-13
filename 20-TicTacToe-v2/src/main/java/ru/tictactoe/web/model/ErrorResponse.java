package ru.tictactoe.web.model;

import java.util.UUID;

public class ErrorResponse {
    private String message;
    private UUID gameId;

    public ErrorResponse() {}

    public ErrorResponse(String message, UUID gameId) {
        this.message = message;
        this.gameId = gameId;
    }

    public String getMessage() {
        return message;
    }
    public UUID getGameId() { return gameId; }
}