package ru.tictactoe.web.model;

import java.util.UUID;

public class PVPIsEndAndDrawStateResponse {
    private UUID gameUUId;
    private int[][] board;
    private GameStatus gameStatus;

    public PVPIsEndAndDrawStateResponse() {};

    public PVPIsEndAndDrawStateResponse(UUID gameUUID, int[][] board, GameStatus gameStatus) {
        this.gameUUId = gameUUID;
        this.board = board;
        this.gameStatus = gameStatus;
    }

    public UUID getGameId() { return gameUUId; }
    public int[][] getBoard() { return board; }
    public GameStatus getGameStatus() { return gameStatus; }
}
