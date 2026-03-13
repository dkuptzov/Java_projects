package ru.tictactoe.domain.service;

import ru.tictactoe.domain.model.Field;
import ru.tictactoe.web.model.GameStatus;

public class GameResult implements GameResultService {
    private static final int SIZE = 3;

    @Override
    public GameStatus theEnd(int[][] board) {
        for (int x = 0; x < SIZE; x++) {
            if (board[x][0] == board[x][1] &&
                    board[x][1] == board[x][2] && board[x][0] != 0) {
                return gameStatus(board[x][0]);
            }
        }
        for (int y = 0; y < SIZE; y++) {
            if (board[0][y] == board[1][y] &&
                    board[1][y] == board[2][y] && board[0][y] != 0) {
                return gameStatus(board[0][y]);
            }
        }
        if (board[0][0] == board[1][1] &&
                board[1][1] == board[2][2] && board[0][0] != 0) {
            return gameStatus(board[0][0]);
        }
        if (board[0][2] == board[1][1] &&
                board[1][1] == board[2][0] && board[0][2] != 0) {
            return gameStatus(board[0][2]);
        }
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == Field.EMPTY.getValue())
                    return GameStatus.IN_PROGRESS;
            }
        }
        return GameStatus.DRAW;
    }

    private GameStatus gameStatus(int status) {
        if (status == 1) return GameStatus.PLAYER_X_WON;
        return GameStatus.PLAYER_O_WON;
    }
}