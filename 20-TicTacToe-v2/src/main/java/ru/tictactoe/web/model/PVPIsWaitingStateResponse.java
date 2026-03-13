package ru.tictactoe.web.model;

import java.util.UUID;

public class PVPIsWaitingStateResponse {
    private UUID gameUUId;
    private int[][] board;
    private GameStatus gameStatus;
    private int currentPlayer;

    public PVPIsWaitingStateResponse() {};

    public PVPIsWaitingStateResponse(UUID gameUUID, int[][] board, GameStatus gameStatus, int currentPlayer) {
        this.gameUUId = gameUUID;
        this.board = board;
        this.gameStatus = gameStatus;
        this.currentPlayer = currentPlayer;
    }

    public UUID getGameId() { return gameUUId; }
    public int[][] getBoard() { return board; }
    public GameStatus getGameStatus() { return gameStatus; }
    public int getCurrentPlayer() { return currentPlayer; }
}
