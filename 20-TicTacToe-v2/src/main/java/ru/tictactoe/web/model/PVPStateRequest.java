package ru.tictactoe.web.model;

public class PVPStateRequest {
    private int[][] board;

    public PVPStateRequest() {}

    public PVPStateRequest(int[][] board) {
        this.board = board;
    }

    public int[][] getBoard() { return board; }
}
