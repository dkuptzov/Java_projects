package ru.tictactoe.web.model;

import java.util.UUID;

public class PVPIsEndAndWonStateResponse {
    private UUID gameUUId;
    private int[][] board;
    private UUID playerWonId;
    private GameStatus gameStatus;

    public PVPIsEndAndWonStateResponse() {};

    public PVPIsEndAndWonStateResponse(UUID gameUUID, int[][] board, UUID playerWonId, GameStatus gameStatus) {
        this.gameUUId = gameUUID;
        this.board = board;
        this.playerWonId = playerWonId;
        this.gameStatus = gameStatus;
    }

    public UUID getGameId() { return gameUUId; }
    public int[][] getBoard() { return board; }
    public UUID getPlayerWonId() { return playerWonId; }
    public GameStatus getGameStatus() { return gameStatus; }
}
