package ru.tictactoe.web.model;

public class GameStateRequest {
    private int[][] board;

    public GameStateRequest() {}

    public GameStateRequest(int[][] board) {
        this.board = board;
    }

    public int[][] getBoard() { return board; }
    public void setBoard(int[][] newBoard) { board = newBoard; }
}