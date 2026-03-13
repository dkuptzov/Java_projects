package ru.tictactoe.web.model;
import java.util.UUID;

public class GameStateResponse {
    private UUID gameUUId;
    private int[][] board;
    private GameStatus gameStatus;
    private int userPlayer;

    public GameStateResponse() {}

    public GameStateResponse(UUID gameUUID, int[][] board, GameStatus gameStatus, int userPlayer) {
        this.gameUUId = gameUUID;
        this.board = board;
        this.gameStatus = gameStatus;
        this.userPlayer = userPlayer;
    }

    public UUID getGameUUId() { return gameUUId; }
    public int[][] getBoard() { return board; }
    public GameStatus getGameStatus() { return gameStatus; }
    public int getUserPlayer() { return userPlayer; }
    public void setBoard(int[][] newBoard) { board = newBoard; }
}