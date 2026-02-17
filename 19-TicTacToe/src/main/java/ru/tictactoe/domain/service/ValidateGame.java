package ru.tictactoe.domain.service;

import ru.tictactoe.domain.model.Field;

public class ValidateGame implements ValidateGameService {
    private static final int SIZE = 3;

    public boolean validateGame(int[][] newBoard, int[][] previousBoard, int player) {
        int diffCount = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (previousBoard[i][j] != newBoard[i][j]) {
                    diffCount++;
                    if (previousBoard[i][j] != Field.EMPTY.getValue() || newBoard[i][j] != player) {
                        System.out.println(previousBoard[i][j] + " " + newBoard[i][j] + " " + player);
                        return false;
                    }
                }
            }
        }
        return diffCount == 1;
    }

    public boolean validateBoard(int[][] board) {
        if (board.length != SIZE) return false;
        for (int i = 0; i < SIZE; i++) {
            if (board[i].length != SIZE) return false;
        }
        return true;
    }
}
