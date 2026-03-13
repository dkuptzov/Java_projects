package ru.tictactoe.web.model;

import java.util.UUID;

public class UserJoinGameStateResponse {
    private String message;
    private UUID gameId;

    public UserJoinGameStateResponse() {};

    public UserJoinGameStateResponse(String message, UUID gameId) {
        this.message = message;
        this.gameId = gameId;
    }

    public String getMessage() { return message; }
    public UUID getGameId() { return gameId; }
}
