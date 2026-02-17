package ru.tictactoe.domain.service;

import ru.tictactoe.domain.model.MoveResult;

public interface MiniMaxService {
    MoveResult nextStep(int[][] board, int aiPlayer, int humanPlayer, int curPlayer);
}
