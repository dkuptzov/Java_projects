package ru.tictactoe.web.model;

import java.util.UUID;

public class NewPVPStateResponse {
    private UUID gameUUId;
    private int[][] board;
    private GameStatus gameStatus;
    private int currentPlayer;
    private int user1;

    public NewPVPStateResponse() {};

    public NewPVPStateResponse(UUID gameUUID, int[][] board, GameStatus gameStatus, int currentPlayer, int user1) {
        this.gameUUId = gameUUID;
        this.board = board;
        this.gameStatus = gameStatus;
        this.currentPlayer = currentPlayer;
        this.user1 = user1;
    }

    public UUID getGameUUId() { return gameUUId; }
    public int[][] getBoard() { return board; }
    public GameStatus getGameStatus() { return gameStatus; }
    public int getCurrentPlayer() { return currentPlayer; }
    public int getUser1() { return user1; }
}
