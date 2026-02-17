package ru.tictactoe.domain.model;

public class MoveResult {
    int score;
    int row;
    int col;

    public MoveResult(int score) { this.score = score; }
    public MoveResult(int score, int row, int col) {
        this.score = score;
        this.row = row;
        this.col = col;
    }

    public int getScore() { return score; }
    public int getRow() { return row; }
    public int getCol() { return col; }
}