package ru.tictactoe.domain.service;

import ru.tictactoe.domain.model.Field;

public class GameResult implements GameResultService {
    private static final int SIZE = 3;

    @Override
    public int theEnd(int[][] board) {
        for (int x = 0; x < SIZE; x++) {
            if (board[x][0] == board[x][1] &&
                    board[x][1] == board[x][2]) {
                return board[x][0];
            }
        }
        for (int y = 0; y < SIZE; y++) {
            if (board[0][y] == board[1][y] &&
                    board[1][y] == board[2][y]) {
                return board[0][y];
            }
        }
        if (board[0][0] == board[1][1] &&
                board[1][1] == board[2][2]) {
            return board[0][0];
        }
        if (board[0][2] == board[1][1] &&
                board[1][1] == board[2][0]) {
            return board[0][2];
        }
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == Field.EMPTY.getValue())
                    return 0;
            }
        }
        return 3;
    }
}