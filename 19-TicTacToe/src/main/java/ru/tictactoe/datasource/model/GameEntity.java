package ru.tictactoe.datasource.model;

import java.util.UUID;

public class GameEntity {
    private final UUID gameUUID;
    private final int[][] board;
    private int currentPlayer;
    private int humanPlayer;
    private int aiPlayer;

    public GameEntity (int[][] board, UUID gameUUID, int currentPlayer, int humanPlayer, int aiPlayer) {
        this.gameUUID = gameUUID;
        this.board = board;
        this.currentPlayer = currentPlayer;
        this.humanPlayer = humanPlayer;
        this.aiPlayer = aiPlayer;
    }

    public UUID getGameUUID() { return gameUUID; }
    public int[][] getBoard() { return board; }
    public int getCurrentPlayer() { return currentPlayer; }
    public int getHumanPlayer() { return humanPlayer; }
    public int getAiPlayer() { return aiPlayer; }
}
