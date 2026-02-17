package ru.tictactoe.domain.service;

import ru.tictactoe.domain.model.Field;
import ru.tictactoe.domain.model.MoveResult;

public class MiniMax implements MiniMaxService {
    private static final int SIZE = 3;
    private static final int AI_WIN_SCORE = 10;
    private static final int HUMAN_WIN_SCORE = -10;
    private final GameResult gameResult = new GameResult();

    @Override
    public MoveResult nextStep(int[][] board, int aiPlayer, int humanPlayer, int curPlayer) {
        /*
        Рекурсия Мини-макс
         */
        int gameState = gameResult.theEnd(board);
        if (gameState != 0) {
            if (gameState == 3) return new MoveResult(0); // ничья
            int score = (gameState == aiPlayer) ? AI_WIN_SCORE : HUMAN_WIN_SCORE;
            return new MoveResult(score);
        }
        int empty = Field.EMPTY.getValue();
        int bestCol = -1, bestRow = -1;
        int bestScore;
        if (curPlayer == aiPlayer) bestScore = Integer.MIN_VALUE;
        else bestScore = Integer.MAX_VALUE;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == empty) {
                    int[][] newBoard = deepCopy(board);
                    newBoard[i][j] = curPlayer;
                    int check = gameResult.theEnd(newBoard);
                    MoveResult result;
                    switch (check) {
                        case 0 -> {
                            int nextPlayer = (curPlayer == aiPlayer) ? humanPlayer : aiPlayer;
                            result = nextStep(newBoard, aiPlayer, humanPlayer, nextPlayer);
                        }
                        case 1, 2 -> result = new MoveResult((check == aiPlayer) ? AI_WIN_SCORE : HUMAN_WIN_SCORE);
                        default -> result = new MoveResult(0);
                    }
                    if (curPlayer == aiPlayer) {
                        if (result.getScore() > bestScore) {
                            bestScore = result.getScore();
                            bestRow = i;
                            bestCol = j;
                        }
                    } else {
                        if (result.getScore() < bestScore) {
                            bestScore = result.getScore();
                            bestRow = i;
                            bestCol = j;
                        }
                    }
                }
            }
        }
        return new MoveResult(bestScore, bestRow, bestCol);
    }

    private int[][] deepCopy(int[][] board) {
        /*
        Копирование поля
         */
        int[][] newBoard = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++)
            System.arraycopy(board[i], 0, newBoard[i], 0, SIZE);
        return newBoard;
    }
}