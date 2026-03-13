package ru.tictactoe.domain.model;

import java.util.Random;
import java.util.UUID;

public class CurrentGame {
    private final UUID gameUUID;
    private final TicTacToe board;
    private int currentPlayer;
    private final int humanPlayer;
    private final int aiPlayer;

    public CurrentGame () {
        this.gameUUID = UUID.randomUUID();
        this.board = new TicTacToe();
        this.currentPlayer = selectRandomPlayer();
        this.humanPlayer = new Random().nextBoolean() ?
                Field.PLAYER_X.getValue() : Field.PLAYER_O.getValue();
        this.aiPlayer = (humanPlayer == Field.PLAYER_X.getValue()) ? Field.PLAYER_O.getValue() : Field.PLAYER_X.getValue();
    }

    public CurrentGame (int[][] board, UUID gameUUID, int player, int humanPlayer, int aiPlayer) {
        this.gameUUID = gameUUID;
        this.board = new TicTacToe(board);
        this.currentPlayer = player;
        this.humanPlayer = humanPlayer;
        this.aiPlayer = aiPlayer;
    }

    public UUID getGameUUID() { return gameUUID; }
    public TicTacToe getBoard() { return board; }
    public int getCurrentPlayer() { return currentPlayer; }
    public void setCurrentPlayer(int newCurrentPlayer) { currentPlayer = newCurrentPlayer; }
    public int getHumanPlayer() { return humanPlayer; }
    public int getAiPlayer() { return aiPlayer; }

    private int selectRandomPlayer() {
        return new Random().nextBoolean() ?
                Field.PLAYER_X.getValue() : Field.PLAYER_O.getValue();
    }
}