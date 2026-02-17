package ru.tictactoe.domain.service;

import ru.tictactoe.domain.model.MoveResult;

public interface TicTacToeService {
    MoveResult getAIMove(int[][] board, int aiPlayer, int humanPlayer, int curPlayer);
    int isGameFinished(int[][] board);
}
