package ru.tictactoe.web.model;

import java.util.UUID;

public class PVPStateResponse {
    private UUID gameUUId;
    private int[][] board;
    private GameStatus gameStatus;
    private int currentPlayer;
    private UUID currentPlayerId;

    public PVPStateResponse() {}

    public PVPStateResponse(UUID gameUUID, int[][] board, GameStatus gameStatus, int currentPlayer, UUID currentPlayerId) {
        this.gameUUId = gameUUID;
        this.board = board;
        this.gameStatus = gameStatus;
        this.currentPlayer = currentPlayer;
        this.currentPlayerId = currentPlayerId;
    }

    public UUID getGameId() { return gameUUId; }
    public int[][] getBoard() { return board; }
    public GameStatus getGameStatus() { return gameStatus; }
    public int getCurrentPlayer() { return currentPlayer; }
    public UUID getCurrentPlayerID() { return currentPlayerId; }
    public void setCurrentPlayerID(UUID currentPlayerId) { this.currentPlayerId = currentPlayerId; }
}
