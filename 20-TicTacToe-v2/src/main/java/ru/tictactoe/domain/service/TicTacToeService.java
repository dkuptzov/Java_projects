package ru.tictactoe.domain.service;

import ru.tictactoe.domain.model.MoveResult;
import ru.tictactoe.web.model.GameStatus;

public interface TicTacToeService {
    MoveResult getAIMove(int[][] board, int aiPlayer, int humanPlayer, int curPlayer);
    GameStatus isGameFinished(int[][] board);
}
